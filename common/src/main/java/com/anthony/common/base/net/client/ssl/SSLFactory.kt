package com.anthony.common.base.net.client.ssl

import javax.net.ssl.*
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.security.*
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：写了两种方式 默认是信任任何证书 还有一种是信任指定证书
 */
object SSLFactory {
    val x509TrustManager: X509TrustManager
        @Synchronized get() = object : X509TrustManager {
            override fun checkClientTrusted(x509Certificates: Array<X509Certificate>, s: String) {

            }

            override fun checkServerTrusted(x509Certificates: Array<X509Certificate>, s: String) {}

            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }
        }
    //这么用的话，任意证书都会信任，容易被中间人攻击
    val defaultSSLSocketFactory: SSLSocketFactory
        @Synchronized get() {
            try {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, arrayOf<TrustManager>(x509TrustManager), null)
                return sslContext.socketFactory
            } catch (e: GeneralSecurityException) {
                throw AssertionError()
            }

        }

    //以anchor.crt以及它签发的证书作为信任锚点 更加安全
    // 取到证书的输入流
    // 创建 Keystore 包含我们的证书
    // 创建一个 TrustManager 仅把 Keystore 中的证书 作为信任的锚点
    // 用 TrustManager 初始化一个 SSLContext
    val coustomSSLSocketFactory: SSLSocketFactory?
        @Synchronized get() {
            try {
                val `is` = FileInputStream("anchor.crt")

                val cf = CertificateFactory.getInstance("X.509")
                val ca = cf.generateCertificate(`is`)
                val keyStoreType = KeyStore.getDefaultType()
                val keyStore = KeyStore.getInstance(keyStoreType)
                keyStore.load(null)
                keyStore.setCertificateEntry("anchor", ca)
                val algorithm = TrustManagerFactory.getDefaultAlgorithm()
                val trustManagerFactory = TrustManagerFactory.getInstance(algorithm)
                trustManagerFactory.init(keyStore)
                val trustManagers = trustManagerFactory.trustManagers
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, trustManagers, null)
                return sslContext.socketFactory
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: CertificateException) {
                e.printStackTrace()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyStoreException) {
                e.printStackTrace()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            }

            return null
        }
}
