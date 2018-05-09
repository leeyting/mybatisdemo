package com.mybatis.demo.base.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @Author: liyao
 * @Description: RSA非对称加密算法
 * @Date: Created in 2018/05/09 16:56
 */

public class RSAUtil {

    public static Pair<String, String> createKeyPairs() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            generator.initialize(512, new SecureRandom());
            KeyPair pair = generator.generateKeyPair();
            PublicKey pubKey = pair.getPublic();
            PrivateKey privKey = pair.getPrivate();
            byte[] pk = pubKey.getEncoded();
            byte[] privk = privKey.getEncoded();
            String strpk = new String(Base64.encodeBase64(pk));
            String strprivk = new String(Base64.encodeBase64(privk));
            System.out.println("公钥Base64编码:" + strpk);
            System.out.println("私钥Base64编码:" + strprivk);
            return Pair.of(strpk, strprivk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptData(String data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] dataToEncrypt = data.getBytes("utf-8");
            byte[] encryptedData = cipher.doFinal(dataToEncrypt);
            String encryptString = Base64.encodeBase64String(encryptedData);
            return encryptString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptData(String data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] descryptData = Base64.decodeBase64(data);
            byte[] descryptedData = cipher.doFinal(descryptData);
            String srcData = new String(descryptedData, "utf-8");
            return srcData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Pair<String, String> keyPairs = createKeyPairs();
            String strpk = keyPairs.getLeft();
            String strprivk = keyPairs.getRight();
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(strpk.getBytes()));
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(strprivk.getBytes()));
            KeyFactory keyf = KeyFactory.getInstance("RSA", "BC");
            PublicKey pubKey = keyf.generatePublic(pubX509);
            PrivateKey privKey = keyf.generatePrivate(priPKCS8);
            String data = "测试RSA加密";
            System.out.println("加密前字符串：data=" + data);
            String encryptData = null;
            if (pubKey != null && (data != null && !data.equals(""))) {
                encryptData = RSAUtil.encryptData(data, pubKey);
                System.out.println("加密后字符串：encryptData=" + encryptData);
            }
            String descryptData = null;
            if (privKey != null && (encryptData != null && !encryptData.equals(""))) {
                descryptData = RSAUtil.decryptData(encryptData, privKey);
                System.out.println("解密后字符串：descryptData=" + descryptData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
