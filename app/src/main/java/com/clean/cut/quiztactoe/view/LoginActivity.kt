package com.clean.cut.quiztactoe.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {

    private val signIn: Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.clean.cut.quiztactoe.R.layout.activity_login_activity)

        val signInButton: SignInButton = findViewById(com.clean.cut.quiztactoe.R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestId()
            .build()

        var googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account != null){
            Log.v("primjer", "already singed in")
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        signInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, signIn)
        }
        //If GoogleSignIn.getLastSignedInAccount returns a GoogleSignInAccount object (rather than null),
        // the user has already signed in to your app with Google. Update your UI accordingly—that is, hide the sign-in button,
        // launch your main activity, or whatever is appropriate for your app.

        //If GoogleSignIn.getLastSignedInAccount returns null, the user has not yet signed in to your app with Google. Update your UI to display the Google Sign-in button.
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == signIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Snackbar.make(this.window.decorView.rootView, "Logged in", Snackbar.LENGTH_SHORT).show()
            Snackbar.make(this.window.decorView.rootView, "${account?.displayName}", Snackbar.LENGTH_SHORT).show()

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()

        } catch (e: ApiException) {
            Snackbar.make(this.window.decorView.rootView, "Error", Snackbar.LENGTH_SHORT).show()
        }

    }

}
