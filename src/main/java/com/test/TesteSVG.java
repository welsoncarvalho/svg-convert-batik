package com.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class TesteSVG {

    private static final String SVG = "<svg width=\"550\" height=\"360\"><g transform=\"translate(180,180)\"><path d=\"M7.34788079488412e-15,-120A120,120,0,0,1,64.29921539747959,-101.31935106024181L0,0Z\" fill=\"#00B8C0\"></path><path d=\"M64.29921539747959,-101.31935106024181A120,120,0,0,1,119.05376415773735,-15.039988027716477L0,0Z\" fill=\"#8ED5E7\"></path><path d=\"M119.05376415773735,-15.039988027716477A120,120,0,0,1,51.09351498780869,108.57924629592235L0,0Z\" fill=\"#C1FEF6\"></path><path d=\"M51.09351498780869,108.57924629592235A120,120,0,0,1,-111.57317830659018,44.17494632216133L0,0Z\" fill=\"#FEA928\"></path><path d=\"M-111.57317830659018,44.17494632216133A120,120,0,0,1,-2.2043642384652355e-14,-120L0,0Z\" fill=\"#EEFF9F\"></path><g class=\"slegend\" transform=\"translate(190,-55)\"><rect width=\"18\" height=\"18\" style=\"fill: rgb(0, 184, 192); stroke: rgb(0, 184, 192);\"></rect><text x=\"22\" y=\"14\">TÍTULO PÚBLICO</text></g><g class=\"slegend\" transform=\"translate(190,-33)\"><rect width=\"18\" height=\"18\" style=\"fill: rgb(142, 213, 231); stroke: rgb(142, 213, 231);\"></rect><text x=\"22\" y=\"14\">TÍTULO PRIVADO</text></g><g class=\"slegend\" transform=\"translate(190,-11)\"><rect width=\"18\" height=\"18\" style=\"fill: rgb(193, 254, 246); stroke: rgb(193, 254, 246);\"></rect><text x=\"22\" y=\"14\">FUNDOS DE INVESTIMENTOS</text></g><g class=\"slegend\" transform=\"translate(190,11)\"><rect width=\"18\" height=\"18\" style=\"fill: rgb(254, 169, 40); stroke: rgb(254, 169, 40);\"></rect><text x=\"22\" y=\"14\">IMÓVEIS</text></g><g class=\"slegend\" transform=\"translate(190,33)\"><rect width=\"18\" height=\"18\" style=\"fill: rgb(238, 255, 159); stroke: rgb(238, 255, 159);\"></rect><text x=\"22\" y=\"14\">RENDA VARIÁVEL</text></g></g></svg>";

    public static void main(String[] args) throws Exception {

        String svgFile = SVG.replace("<svg", "<svg xmlns=\"http://www.w3.org/2000/svg\"");

        InputStream svgFileStream;

        try {
            svgFileStream = new ByteArrayInputStream(svgFile.getBytes("UTF-8"));

        } catch (UnsupportedEncodingException e) {
            svgFileStream = new ByteArrayInputStream(svgFile.getBytes());
            System.out.println("Could not get StringBytes in UTF-8");
            e.printStackTrace();
        }

        TranscoderInput inputSvgImage = new TranscoderInput(svgFileStream);
        PNGTranscoder converter = new PNGTranscoder();

        ByteArrayOutputStream pngFileStream = new ByteArrayOutputStream();
        TranscoderOutput outputPngImage = new TranscoderOutput(pngFileStream);

        converter.transcode(inputSvgImage, outputPngImage);

        String pngFile = Base64.getEncoder().encodeToString(pngFileStream.toByteArray());

        System.out.println("data:image/png;base64," + pngFile);

    }

}