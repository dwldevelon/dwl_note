package dwl;

import cn.hutool.extra.compress.CompressUtil;
import cn.hutool.extra.compress.archiver.Archiver;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wenlong.ding
 * @date 2021/1/27 10:57
 */
public class CompressUtilDemo {


    /**
     * hutool打包代码demo
     */
    @Test
    public void a(){
        String basePath = "D:\\tmp\\old\\";
        List<String> fileNames = new ArrayList<>();
        fileNames.add(basePath+"policy013101-guacont-01.pdf");
        fileNames.add(basePath+"policy013101-guacontract-01.pdf");
        String localTarFileName = basePath + "test.tar";
        Archiver archiver = CompressUtil.createArchiver(StandardCharsets.UTF_8, ArchiveStreamFactory.TAR, new File(localTarFileName));
        fileNames.stream().map(File::new).forEach(archiver::add);
        archiver.finish();
        archiver.close();
        System.out.println("---end---");
    }

}
