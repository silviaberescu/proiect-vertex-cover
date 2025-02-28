# Variabile
PROJECT_NAME=my-project
JAR_NAME=$(PROJECT_NAME)-1.0-SNAPSHOT.jar
TARGET_DIR=target
JVM_OPTS=-Xmx512m
SRC_DIR=src/main/java
TEST_DIR=src/main/java/org/tests
OUTPUT_DIR=src/main/java/org/output

# Regula principală de compilare
all: clean install

# Curăță fișierele generate anterior
clean:
	mvn clean

# Rulează compilarea și testarea proiectului
install:
	mvn clean install

# Rulează aplicația
run: install
	java $(JVM_OPTS) -jar $(TARGET_DIR)/$(JAR_NAME)

# Rulează benchmark-ul
benchmark: install
	java $(JVM_OPTS) -jar $(TARGET_DIR)/$(JAR_NAME)

# Rulează testele pentru VertexCoverBrute
run_tests_brute:
	@for t in $(shell seq 1 25); do \
		echo "Running brute force test $$t..."; \
		java $(JVM_OPTS) -cp $(TARGET_DIR)/$(JAR_NAME):$(SRC_DIR) org.algorithms.VertexCoverBrute $$t; \
	done

# Rulează testele pentru HeuristicApproxVertexCover
run_tests_heuristic:
	@for t in $(shell seq 1 25); do \
		echo "Running heuristic approximation test $$t..."; \
		java $(JVM_OPTS) -cp $(TARGET_DIR)/$(JAR_NAME):$(SRC_DIR) org.algorithms.HeuristicApproxVertexCover $$t; \
	done

# Rulează testele pentru VertexCoverBinarySearch
run_tests_binary_search:
	@for t in $(shell seq 1 25); do \
		echo "Running binary search test $$t..."; \
		java $(JVM_OPTS) -cp $(TARGET_DIR)/$(JAR_NAME):$(SRC_DIR) org.algorithms.VertexCoverBinarySearch $$t; \
	done

# Rulează testele pentru VertexCoverTree
run_tests_tree:
	@for t in $(shell seq 22 25); do \
		echo "Running tree-based vertex cover test $$t..."; \
		java $(JVM_OPTS) -cp $(TARGET_DIR)/$(JAR_NAME):$(SRC_DIR) org.algorithms.VertexCoverTree $$t; \
	done

# Regula pentru a instala doar dependențele
dependencies:
	mvn dependency:resolve

# Regula pentru a verifica starea proiectului
status:
	mvn validate
