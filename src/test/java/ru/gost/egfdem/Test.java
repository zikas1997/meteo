package ru.gost.egfdem;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.gost.egfdem.service.FileParser;

import java.io.File;
import java.io.FileInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Ignore
public class Test {

   @Autowired
   @Qualifier("atmosphereParser")
    private FileParser atmosphereParser;

    @Autowired
    @Qualifier("radiationParser")
    private FileParser radiationParser;

    @Autowired
    @Qualifier("ostankinoParser")
    private FileParser ostankinoParser;

   @org.junit.Test
   public void testAtmosphereParser() throws Exception{
       File file = new File("C:\\example\\0mtp20200203.txt");
       atmosphereParser.parseFile(new FileInputStream(file), file.getName());
   }

    @org.junit.Test
    public void testRadiationParser()  throws Exception{
        File file = new File("C:\\example\\[MEASURE]_[917]_[inv_DAVIS VANTAGE PRO2 Wind-Rain]_[22-12-2018_00-00].CSV");
        radiationParser.parseFile(new FileInputStream(file), file.getName());
    }

    @org.junit.Test
    public void testOstankinoParser()  throws Exception{
        File file = new File("C:\\example\\F1-503.DAT.csv");
        ostankinoParser.parseFile(new FileInputStream(file), file.getName());
    }

}
