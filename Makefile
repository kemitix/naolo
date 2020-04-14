VERSION=DEV-SNAPSHOT

graphs:
	mvn validate

domain:
	dot -T png doc/entities.dot > doc/images/entities.png

run-dev:
	mvn -pl runner-quarkus quarkus:dev

native:
	mvn verify -Pnative

run-native:
	./runner-quarkus/target/naolo-runner-quarkus-${VERSION}-runner
