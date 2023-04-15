import org.bouncycastle.crypto.digests.SM3Digest;

public class Sm3Test {

    /**
     * sm3 加密
     * @param args
     */
    public static void main(String[] args) {
        byte[] asciiArray = ascii("gybx1234");
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(asciiArray, 0, asciiArray.length);
        byte[] encyptByte = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(encyptByte,0);
        System.out.println(encyptByte);
        String hex = bytesToHex(encyptByte);
        System.out.println(hex);
    }

    public static byte[] ascii(String str) {

        char [] ch= str.toCharArray();
        byte[] bytesArray = new byte[ch.length];
        for (int i = 0; i < ch.length; i++) {
           bytesArray[i]=(byte)ch[i];

        }
        return bytesArray;

    }
    public static String bytesToHex(byte[] bytes){
        StringBuffer sb= new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
