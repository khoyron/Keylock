package android.actionsheet.demo.com.khoiron.locklib

import android.Manifest
import android.actionsheet.demo.com.khoiron.locklib.FingerPrintActivity.contant.FIRST
import android.actionsheet.demo.com.khoiron.locklib.FingerPrintActivity.contant.NOTCANCELLED
import android.actionsheet.demo.com.khoiron.locklib.FingerPrintActivity.contant.NOTFIRST
import android.annotation.TargetApi
import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.widget.TextView
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

class FingerPrintActivity : BaseActivity() {



    private var keyStore: KeyStore? = null
    // Variable used for storing the key in the Android Keystore container
    private val KEY_NAME = "androidHive"
    private var cipher: Cipher? = null
    private var textView: TextView? = null
    var notcancell = false

    val desc by lazy { findViewById(R.id.desc) as TextView }
    val tittle_finger by lazy { findViewById(R.id.tittle_finger) as TextView }
    var insert = false

    override fun getLayout(): Int {
        return R.layout.fingger_layout
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onMain(savedInstanceState: Bundle?) {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

        textView = findViewById(R.id.errorText)

        try {
            if (intent.getStringExtra(FIRST)!=null){
                if (FIRST.equals(intent.getStringExtra(FIRST))) {
                    insert = true
                    tittle_finger.setText("Please Insert Finggerprint");
                }else if(NOTCANCELLED.equals(intent.getStringExtra(FIRST))){
                    notcancell = true
                    insert = true
                    tittle_finger.setText("Please Insert Finggerprint");
                }
            }else if (intent.getStringExtra(NOTFIRST)!= null){
                if (NOTCANCELLED.equals(intent.getStringExtra(NOTFIRST))) {
                    notcancell = true
                }
            }
            /*val first = intent.getStringExtra("first")
            if (FIRST.equals(first)) {
                insert = true
                tittle_finger.setText("Please Insert Finggerprint");
            }else if (NOTCANCELLED.equals(first)){
                notcancell = true
            }*/
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Check whether the device has a Fingerprint sensor.
        if (!fingerprintManager.isHardwareDetected) {
            /**
             * An error message will be displayed if the device does not contain the fingerprint hardware.
             * However if you plan to implement a default authentication method,
             * you can redirect the user to a default authentication activity from here.
             * Example:
             * Intent intent = new Intent(this, DefaultAuthenticationActivity.class);
             * startActivity(intent);
             */
            textView!!.text = "Your Device does not have a Fingerprint Sensor"
        } else {
            // Checks whether fingerprint permission is set on manifest
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                textView!!.text = "Fingerprint authentication permission not enabled"
            } else {
                // Check whether at least one fingerprint is registered
                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    textView!!.text = "Register at least one fingerprint in Settings"
                    textView!!.setOnClickListener { startActivity(Intent(Settings.ACTION_SECURITY_SETTINGS)) }
                } else {
                    // Checks whether lock screen security is enabled or not
                    if (!keyguardManager.isKeyguardSecure) {
                        textView!!.text = "Lock screen security not enabled in Settings"
                    } else {
                        generateKey()


                        if (cipherInit()) {
                            val cryptoObject = FingerprintManager.CryptoObject(cipher!!)
                            val helper = FingerprintHandler(this,insert)
                            helper.startAuth(fingerprintManager, cryptoObject)
                        }
                    }
                }
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    protected fun generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
        } catch (e: Exception) {
            e.printStackTrace()
        }


        val keyGenerator: KeyGenerator
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get KeyGenerator instance", e)
        } catch (e: NoSuchProviderException) {
            throw RuntimeException("Failed to get KeyGenerator instance", e)
        }


        try {
            keyStore!!.load(null)
            keyGenerator.init(KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build())
            keyGenerator.generateKey()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        } catch (e: InvalidAlgorithmParameterException) {
            throw RuntimeException(e)
        } catch (e: CertificateException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }


    @TargetApi(Build.VERSION_CODES.M)
    fun cipherInit(): Boolean {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: NoSuchPaddingException) {
            throw RuntimeException("Failed to get Cipher", e)
        }


        try {
            keyStore!!.load(
                    null)
            val key = keyStore!!.getKey(KEY_NAME, null) as SecretKey
            cipher!!.init(Cipher.ENCRYPT_MODE, key)
            return true
        } catch (e: KeyPermanentlyInvalidatedException) {
            return false
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: CertificateException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: UnrecoverableKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: IOException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to init Cipher", e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException("Failed to init Cipher", e)
        }

    }

    object contant{
        var FIRST = "first"
        var NOTFIRST = "notfirst"
        var NOTCANCELLED = "notcancell"
        var FINGER = 20
    }

    override fun onBackPressed() {
        if(!notcancell){
            val returnIntent = Intent()
            setResult(Activity.RESULT_CANCELED, returnIntent)
            finish()
        }
    }
}
