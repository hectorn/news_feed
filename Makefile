.PHONY: build

build:
	./gradlew clean build

test:
	./gradlew test --warning-mode all

test-mutation:
	./gradlew pitest

run:
	./gradlew bootRun

run-docker: build
	docker-compose up