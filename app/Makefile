.PHONY:build

run-dist:
	./build/install/app/bin/app src/main/resources/file1.json src/main/resources/file2.json
	./build/install/app/bin/app src/main/resources/file1.yaml src/main/resources/file2.yaml
	./build/install/app/bin/app -f stylish src/main/resources/file1.json src/main/resources/file2.json
	./build/install/app/bin/app -f stylish src/main/resources/file1.yaml src/main/resources/file2.yaml
	./build/install/app/bin/app -f plain src/main/resources/file1.json src/main/resources/file2.json
	./build/install/app/bin/app -f plain src/main/resources/file1.yaml src/main/resources/file2.yaml
	./build/install/app/bin/app -f json src/main/resources/file1.json src/main/resources/file2.json
	./build/install/app/bin/app -f json src/main/resources/file1.yaml src/main/resources/file2.yaml

build:
	./gradlew clean installDist

test:
	./gradlew test
report:
	./gradlew jacocoTestReport