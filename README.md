# Appium - Cucumber - Project

#### Introduction

Auto-mobile is Appium Cucumber based project to automate iOS, Android Native/Hybrid applications.
Junit runner is used to call cucumber tests.

Features are to be written in `/auto-mobile/src/test/resources/tests/features`
Desired capabilities (json format) are to be stored in `/auto-mobile/src/test/resources/capabilities`
Apk or ipa (binaries) to be stored in `/auto-mobile/src/test/resources/tests/binaries`
Cucumber html reports are captured in `auto-mobile/testout/reports`


### Desired Capabilities

Desired capabilities are stored in json format as stored in Appium desktop.
Based on json file name (starts with ios or android) Appium Driver will be initiated.

System property `capabilities.identifier` to be set to indicate capabilities for running tests.

Ex: capabilities.identifier=ios or capabilities.identifier=android

### Appium Server Configuration

Below system properties to be set while running tests if appium server is running already in the server.

1. `appium.server.url` to be set with server url ex: `http://127.0.0.1:4723/wd/hub`
2. `appium.log.level` can be overridden and default value is 'error'

if `appium.server.url` is not set in the System properties, then framework will start Appium server in the localhost
on 4723 port (by default). If 4723 is occupied, then Appiumn server will start on next available port.

### Device Id can be overridden

Though udid is a part of desired capabilities, but it can be overridden by setting `device.id` property.


### Run Tests


`$ mvn test "-Dcucumber.options=--tags @tag1" -Dcapabilities.identifier=ios`


            











