VERSION=DEV-SNAPSHOT
HOST := $(shell ip address|grep 192.168|cut -d\  -f6|cut -d\/ -f1)

graphs:
	mvn validate

domain:
	dot -T png doc/entities.dot > doc/images/entities.png

start-dev: server.PID run-webui

server.PID:
	{ mvn -pl runner-quarkus quarkus:dev & echo $$! > $@; }
	@echo Server started: $(cat $@)
	sleep 5

stop-dev:
	kill `cat server.PID` && rm server.PID

run-dev:
	mvn -pl runner-quarkus quarkus:dev

run-webui:
	(cd webui/src/main/web ; npm run start)

native:
	mvn verify -Pnative -DskipTests -DskipITs -Dpitest.skip

run-native:
	./runner-quarkus/target/naolo-runner-quarkus-${VERSION}-runner

run-docker:
	docker build \
		--file runner-quarkus/src/main/docker/Dockerfile.jvm \
		--tag naolo-dev \
		runner-quarkus/
	docker run --rm \
		-p 8080:8080 \
		-e QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://${HOST}/ \
		naolo-dev
