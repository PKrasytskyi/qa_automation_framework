# A single, reusable image for all TestNG suites in this repository.
FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /workspace

# Chromium and its matching driver are installed together, so Selenium can use
# the driver already available in PATH without downloading it at test runtime.
RUN apt-get update \
    && apt-get install -y --no-install-recommends chromium chromium-driver \
    && rm -rf /var/lib/apt/lists/*

ENV CHROME_BIN=/usr/bin/chromium \
    CHROMEDRIVER_PATH=/usr/bin/chromedriver \
    HEADLESS=true

# Cache Maven dependencies in a separate layer. Source changes will not force
# the dependency download to run again.
COPY pom.xml ./
RUN mvn --batch-mode --no-transfer-progress dependency:go-offline

COPY . .
COPY docker-entrypoint.sh /usr/local/bin/run-suite
RUN chmod +x /usr/local/bin/run-suite

ENTRYPOINT ["run-suite"]
CMD ["smoke"]
