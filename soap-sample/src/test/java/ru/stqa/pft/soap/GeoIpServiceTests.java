package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("87.4.4.247");
        assertTrue(ipLocation.contains("IT"));
    }
}
