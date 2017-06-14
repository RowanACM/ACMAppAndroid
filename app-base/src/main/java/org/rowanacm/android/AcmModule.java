package org.rowanacm.android;

import dagger.Module;

@Module
public class AcmModule {

    public AcmModule() {

    }

    /*

    @Provides
    @Singleton
    FirebaseRemoteConfig providesFirebaseRemoteConfig() {
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        firebaseRemoteConfig.setConfigSettings(configSettings);
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
        return firebaseRemoteConfig;
    }

    @Provides
    @Singleton
    RemoteConfig providesRemoteConfig(FirebaseRemoteConfig firebaseRemoteConfig, Context context) {
        return new RemoteConfig(firebaseRemoteConfig,  context);
    }

    @Provides
    @Singleton
    GoogleSignInOptions providesGoogleSignInOptions(Context context) {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    @Provides
    @Singleton
    GoogleApiClient providesGoogleApiClient(Context context, GoogleSignInOptions options) {
        return new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();
    }

    @Provides
    @Singleton
    FirebaseAuth providesFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }


    @Provides
    @Singleton
    DatabaseReference providesFirebaseDatabaseReference() {
        return null; //FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    FirebaseMessaging providesFirebaseMessaging() {
        return FirebaseMessaging.getInstance();
    }


    @Provides
    @Singleton
    FirebaseDatabase providesFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    @Singleton
    AdminManager providesAdminManager() {
        return new AdminManager();
    }

    @Provides
    @Singleton
    AttendanceClient providesAttendanceClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://2dvdaw7sq1.execute-api.us-east-1.amazonaws.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(AttendanceClient.class);
    }

    */
}