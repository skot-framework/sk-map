include(":viewcontract")
include(":viewmodel")
include(":viewlegacy")
include(":viewlegacy_mapbox")
include(":viewmodelTests")

fun getMapBoxKey(): String {
    val localProperties = java.util.Properties()
    localProperties.load(java.io.FileInputStream(File(rootDir, "local.properties")))
    return localProperties.getProperty("mapBoxPrivateKey")
}



dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        maven {
            setUrl("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password

                password = getMapBoxKey()

            }
        }
    }
}