package com.justapp.photofeed.data.keystore;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Base64;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

/**
 * Реализация {@link KeyStoreManager}
 *
 * @author Sergey Rodionov
 */
public final class KeyStoreManagerImpl implements KeyStoreManager {
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String AES_MODE = "AES/GCM/NoPadding";
    private static final String RSA_MODE = "RSA/ECB/PKCS1Padding";
    private static final String KEY_ALGORITHM_RSA = "RSA";
    private static final String KEYSTORE_ALIAS = "KEYSTORE";

    private KeyStore mKeyStore;
    private KeyStoreHelper mPreferencesHelper;

    public KeyStoreManagerImpl(@NonNull Context context,
                               @NonNull KeyStoreHelper keyStoreHelper) {
        try {
            mPreferencesHelper = keyStoreHelper;
            mKeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER);
            mKeyStore.load(null);

            if (!mKeyStore.containsAlias(KEYSTORE_ALIAS)) {
                mPreferencesHelper.setIV("");
                generateKeyStoreKey(context);
                generateAESKey();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveToken(@NonNull String token) {
        mPreferencesHelper.setToken(encrypt(token));
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public String getToken() {
        return decrypt(mPreferencesHelper.getToken());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteToken() {
        mPreferencesHelper.clearToken();
    }

    @NonNull
    private String encrypt(String plainText) {
        try {
            return encryptAES(plainText);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    private String decrypt(String encryptedText) {
        try {
            return decryptAES(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void generateKeyStoreKey(Context context) throws NoSuchProviderException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            generateRSAKeyApiM();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            generateRSAKeOldApi(context);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generateRSAKeyApiM() throws NoSuchProviderException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEYSTORE_PROVIDER);

        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec
                .Builder(KEYSTORE_ALIAS, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .build();

        keyPairGenerator.initialize(keyGenParameterSpec);
        keyPairGenerator.generateKeyPair();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void generateRSAKeOldApi(Context context) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.YEAR, 30);

        KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                .setAlias(KEYSTORE_ALIAS)
                .setSubject(new X500Principal("CN=" + KEYSTORE_ALIAS))
                .setSerialNumber(BigInteger.TEN)
                .setStartDate(start.getTime())
                .setEndDate(end.getTime())
                .build();

        KeyPairGenerator keyPairGenerator = KeyPairGenerator
                .getInstance(KEY_ALGORITHM_RSA, KEYSTORE_PROVIDER);

        keyPairGenerator.initialize(spec);
        keyPairGenerator.generateKeyPair();
    }

    private String encryptRSA(byte[] plainText) throws KeyStoreException, InvalidKeyException,
            NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException {

        PublicKey publicKey = mKeyStore.getCertificate(KEYSTORE_ALIAS).getPublicKey();
        Cipher cipher = Cipher.getInstance(RSA_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedByte = cipher.doFinal(plainText);

        return Base64.encodeToString(encryptedByte, Base64.DEFAULT);
    }

    private byte[] decryptRSA(String encryptedText) throws UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, NoSuchPaddingException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        PrivateKey privateKey = (PrivateKey) mKeyStore.getKey(KEYSTORE_ALIAS, null);
        Cipher cipher = Cipher.getInstance(RSA_MODE);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedBytes = Base64.decode(encryptedText, Base64.DEFAULT);

        return cipher.doFinal(encryptedBytes);
    }

    private void generateAESKey() throws NoSuchPaddingException, NoSuchAlgorithmException,
            KeyStoreException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {

        byte[] aesKey = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(aesKey);

        byte[] generated = secureRandom.generateSeed(12);
        String iv = Base64.encodeToString(generated, Base64.DEFAULT);
        mPreferencesHelper.setIV(iv);

        String encryptAESKey = encryptRSA(aesKey);
        mPreferencesHelper.setAESKey(encryptAESKey);
    }

    private String encryptAES(String plainText) throws NoSuchPaddingException,
            NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException,
            IllegalBlockSizeException, KeyStoreException, BadPaddingException,
            InvalidAlgorithmParameterException {

        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, getAESKey(), new IvParameterSpec(getIV()));
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    @NonNull
    private String decryptAES(String encryptedText) throws NoSuchPaddingException,
            NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException,
            IllegalBlockSizeException, KeyStoreException, BadPaddingException,
            InvalidAlgorithmParameterException {

        byte[] decodedBytes = Base64.decode(encryptedText.getBytes(), Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.DECRYPT_MODE, getAESKey(), new IvParameterSpec(getIV()));

        return new String(cipher.doFinal(decodedBytes));
    }

    private byte[] getIV() {
        String prefIV = mPreferencesHelper.getIV();
        return Base64.decode(prefIV, Base64.DEFAULT);
    }

    @NonNull
    private SecretKeySpec getAESKey() throws NoSuchPaddingException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeyException {

        String encryptedKey = mPreferencesHelper.getAESKey();
        byte[] aesKey = decryptRSA(encryptedKey);

        return new SecretKeySpec(aesKey, AES_MODE);
    }

}
