package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto virheellinenVarasto;
    Varasto puoliTaysiVarasto;
    Varasto liianTaysiVarasto;
    Varasto puoliTaysiVirheellinenVarasto;
    Varasto negatiivinenSaldoVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        virheellinenVarasto = new Varasto(-10);
        puoliTaysiVarasto = new Varasto(10, 5);
        liianTaysiVarasto = new Varasto(10,15);
        puoliTaysiVirheellinenVarasto = new Varasto(0, 10);
        negatiivinenSaldoVarasto = new Varasto(10, -10);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVirheellisenVaraston() {
        assertEquals(0, virheellinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoPuoliTaydenVaraston() {
        assertEquals(5, puoliTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoLiianTaydenVaraston() {
        assertEquals(10, liianTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoPuoliTaydenVirheellisenVaraston() {
        assertEquals(0, puoliTaysiVirheellinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoNegatiivisenSaldonVaraston() {
        assertEquals(0, negatiivinenSaldoVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void listataanNegatiivinenMaara() {
        puoliTaysiVarasto.lisaaVarastoon(-10);
        assertEquals(5, puoliTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanLiianPaljon() {
        puoliTaysiVarasto.lisaaVarastoon(15);
        assertEquals(10, puoliTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanNegatiivinenMaara() {
        puoliTaysiVarasto.otaVarastosta(-10);
        assertEquals(5, puoliTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanLiianPaljon() {
        assertEquals(5, puoliTaysiVarasto.otaVarastosta(10), vertailuTarkkuus);
    }
    
    @Test
    public void toStringTulostaaOikein() {
        assertEquals("saldo = 5, vielä tilaa 5", puoliTaysiVarasto.toString());
    }

}