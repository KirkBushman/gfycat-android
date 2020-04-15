# gfycat-android
A lighter and simpler Gfycat API wrapper.

[![](https://jitpack.io/v/KirkBushman/gfycat-android.svg)](https://jitpack.io/#KirkBushman/gfycat-android)

It's built for Android, written in Kotlin, using Moshi, Retrofit and OkHttp.

### How to install.

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.KirkBushman:gfycat-android:Tag'
}
```

### Obtain a client.

```

// Userless Grant:
val creds = ClientCredentials(**Client Id**, **Client Secret**)

// or

// Password Grant:
val creds = PasswordCredentials(**Client Id**, **Client Secret**, **Username**, **Password**)

// now let's use them to authenticate

val auth = AuthManager(creds, LOGGING)

val bearer = auth.getAuthToken(SharedPrefsStorageManager(context))
val client = auth.getGfycatClient(bearer)
```

### Use it to browse Gfycat.

```
val gfycat = client.gfycatFromUrl(uri)
println(gfycat.mp4Url)

val gfycat = client.gfycat(id)
println(gfycat.mp4Url)

val user = client.user(username)
println(gfycat.username + " has " + gfycat.followers + " followers")
```

### License
This project is licensed under the MIT License
