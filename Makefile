
GRAPH_VERSION=1.45

graphs:
	mvn org.fusesource.mvnplugins:maven-graph-plugin:${GRAPH_VERSION}:reactor \
		-Dhide-transitive \
		-Dhide-version
	mv target/reactor-graph.png doc/images/

domain:
	dot -T png doc/entities.dot > doc/images/entities.png
