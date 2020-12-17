* java生成二维码
```
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.3.1</version>
</dependency>
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.3.1</version>
</dependency>
```   
```
 /**
     * 生成二维码
     *
     * @param content 内容
     * @param size    高宽
     * @return 图片
     * @throws WriterException 生成二维码异常
     */
    public static BufferedImage generator(String content, int size) throws WriterException {
        // 二维码属性配置集
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();

        // 识别等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 编码类型
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 线条
        hints.put(EncodeHintType.MARGIN, 1);
        // 生成二维码逻辑数集
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        // 实例化图片对象
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        // 遍历并将逻辑值转为黑白块
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }
```