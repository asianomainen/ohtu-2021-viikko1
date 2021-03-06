package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
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
    public void varastonTilavuusOnNollaJosAlustetaanNegatiivisellaTilavuudella() {
        Varasto v = new Varasto(-1);
        assertEquals(v.getTilavuus(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void varastonTilavuusOnNollaJosAlustetaanNegatiivisellaTilavuudellaJaAlkusaldolla() {
        Varasto v = new Varasto(-1, 10);
        assertEquals(v.getTilavuus(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJosAlustetaanAlkusaldolla() {
        Varasto v = new Varasto(100, 10);
        assertEquals(v.getTilavuus(), 100.0, vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaAlkusaldoJosAlustetaanNegatiivisellaAlkusaldolla() {
        Varasto v = new Varasto(100, -10);
        assertEquals(v.getSaldo(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void varastoonEiLisätäMitäänJosLisättäväMääräOnNegatiivinen() {
        varasto.lisaaVarastoon(-2);
        assertEquals(varasto.getSaldo(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void saldoEiYlitäVarastonTilavuutta() {
        varasto.lisaaVarastoon(12);
        assertEquals(varasto.getSaldo(), 10.0, vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaNegatiivistaMäärää() {
        varasto.otaVarastosta(-2);
        assertEquals(varasto.getSaldo(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaMäärääEnempää() {
        varasto.otaVarastosta(13);
        assertEquals(varasto.getSaldo(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikeanTekstin() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }
}