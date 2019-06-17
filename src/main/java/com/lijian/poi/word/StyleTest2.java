package com.lijian.poi.word;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Random;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;

public class StyleTest2 {
    public static void main(String[] args) throws Exception {
        StyleTest2 t = new StyleTest2();
        XWPFDocument doc = new XWPFDocument();
        // 需关闭护眼色才能看到效果
        //t.setDocumentbackground(doc, "FDE9D9");//设置页面背景色
        t.testSetUnderLineStyle(doc);//设置下划线样式以及突出显示文本
        t.addNewPage(doc, BreakType.PAGE);
        t.testSetShdStyle(doc);//设置文字底纹
        t.saveDocument(doc,"e:/"+ System.currentTimeMillis() + ".docx");
    }

    public void testSetUnderLineStyle(XWPFDocument doc) {
        String[] colors = new String[] { "CCA6EF", "DD999D", "4FCEF0",
                "7A7A7A", "F3C917", "FFA932", "C7B571", "535354", "5FD2F1",
                "B5E900", "FEF8B6" };
        Random random = new Random();
        // 这里为了方便测试写了数字,推荐写英文样式
        for (int i = 1; i <= 18; i++) {
            XWPFParagraph p = doc.createParagraph();
            setParagraphFontInfoAndUnderLineStyle(p, 
                    "本文是以poi3.9读写2010word、2010excel、2010ppt", "华文行楷", "000000","22", 
                    false, false, false, true, 
                    i,colors[Math.abs(random.nextInt(colors.length))], false, 0,null);
            setParagraphSpacingInfo(p, true, "0", "50", false, "0", "0", 
                    true,"240", STLineSpacingRule.Enum.forString("auto"));
            setParagraphAlignInfo(p, ParagraphAlignment.LEFT,TextAlignment.CENTER);
        }
    }

    public void testSetShdStyle(XWPFDocument doc) {
        String[] colors = new String[] { "CCA6EF", "DD999D", "4FCEF0",
                "7A7A7A", "F3C917", "FFA932", "C7B571", "535354", "5FD2F1",
                "B5E900", "FEF8B6" };
        Random random = new Random();
        // 这里为了方便测试写了数字,推荐写英文样式
        for (int i = 1; i <= 38; i++) {
            XWPFParagraph p = doc.createParagraph();
            setParagraphFontInfoAndUnderLineStyle(p, 
                    "本文是以poi3.9读写2010word、2010excel、2010ppt", "华文行楷", "1D8C56","22",
                    false, false, false, false, 
                    i, null, true, i,colors[Math.abs(random.nextInt(colors.length))]);
            setParagraphSpacingInfo(p, true, "0", "50", false, "0", "0", 
                    true,"240", STLineSpacingRule.Enum.forString("auto"));
            setParagraphAlignInfo(p, ParagraphAlignment.LEFT,TextAlignment.CENTER);
        }
    }
    
    //设定水平对齐方式、垂直对齐方式
    public void setParagraphAlignInfo(XWPFParagraph p,
            ParagraphAlignment pAlign, TextAlignment valign) {
        p.setAlignment(pAlign);
        p.setVerticalAlignment(valign);
    }

    //三组数，分别设定  ★段前段后磅数★段前段后行数★间距★
    public void setParagraphSpacingInfo(XWPFParagraph p, boolean isSpace,String before, String after, 
            boolean isPLine, String beforeLines,String afterLines, 
            boolean isLine, String line,STLineSpacingRule.Enum lineValue) {
        CTPPr pPPr = null;
        if (p.getCTP() != null) {
            if (p.getCTP().getPPr() != null) {
                pPPr = p.getCTP().getPPr();
            } else {
                pPPr = p.getCTP().addNewPPr();
            }
        }
        /**
         * CTSpacing设置段落
         */
        CTSpacing pSpacing = pPPr.getSpacing() != null ? pPPr.getSpacing()
                : pPPr.addNewSpacing();
        if (isSpace) {
            // 段前磅数
            if (before != null) {
                pSpacing.setBefore(new BigInteger(before));
            }
            // 段后磅数
            if (after != null) {
                pSpacing.setAfter(new BigInteger(after));
            }
        }
        if (isPLine) {
            // 段前行数
            if (beforeLines != null) {
                pSpacing.setBeforeLines(new BigInteger(beforeLines));
            }
            // 段后行数
            if (afterLines != null) {
                pSpacing.setAfterLines(new BigInteger(afterLines));
            }
        }
        // 间距
        if (isLine) {
            if (line != null) {
                pSpacing.setLine(new BigInteger(line));
            }
            if (lineValue != null) {
                pSpacing.setLineRule(lineValue);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void setParagraphFontInfoAndUnderLineStyle(XWPFParagraph p,
            String content, String fontFamily, String colorVal,String fontSize, 
            boolean isBlod, boolean isItalic,boolean isStrike, boolean isUnderLine, 
            int underLineStyle,String underLineColor, boolean isShd, int shdValue, String shdColor) {
        XWPFRun pRun = null;
        if (p.getRuns() != null && p.getRuns().size() > 0) {
            pRun = p.getRuns().get(0);
        } else {
            pRun = p.createRun();
        }
        pRun.setText(content);
/**
 * CTRPr设置页
 */
        CTRPr pRpr = null;
        if (pRun.getCTR() != null) {
            pRpr = pRun.getCTR().getRPr();
            if (pRpr == null) {
                pRpr = pRun.getCTR().addNewRPr();
            }
        }
/**
 * CTFonts设置字体
 */
        // 设置字体
        CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr
                .addNewRFonts();
        fonts.setAscii(fontFamily);//---只改变Ascii中的(字母和数字)
        fonts.setEastAsia(fontFamily);//---只改变中文EastAsia
        fonts.setHAnsi(fontFamily);//---

        /**
         * CTHpsMeasure设置大小
         */
        // 设置字体大小
        CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
        sz.setVal(new BigInteger(fontSize));

        CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr
                .addNewSzCs();
        szCs.setVal(new BigInteger(fontSize));//---字体大小

        // 设置字体样式
        if (isBlod) {
            pRun.setBold(isBlod);//---是否加黑加粗
        }
        if (isItalic) {
            pRun.setItalic(isItalic);//---是否倾斜
        }
        if (isStrike) {
            pRun.setStrike(isStrike);//是否有中划线
        }
        if (colorVal != null) {
            pRun.setColor(colorVal);//---字体颜色1D8C56
        }

//        // 设置字突出显示文本---设置的文字的背景颜色，太难看了！！
//        if (underLineStyle > 0 && underLineStyle < 17) {
//            CTHighlight hightLight = pRpr.isSetHighlight() ? pRpr
//                    .getHighlight() : pRpr.addNewHighlight();
//            hightLight.setVal(STHighlightColor.Enum.forInt(underLineStyle));
//        }
//
        // 设置下划线样式
        if (isUnderLine) {
            CTUnderline u = pRpr.isSetU() ? pRpr.getU() : pRpr.addNewU();
            u.setVal(STUnderline.Enum.forInt(Math.abs(underLineStyle % 19)));
            if (underLineColor != null) {
                u.setColor(underLineColor);
            }
        }
/**
 * CTShd设置底纹
 */
        if (isShd) {
            // 设置底纹
            CTShd shd = pRpr.isSetShd() ? pRpr.getShd() : pRpr.addNewShd();
            if (shdValue > 0 && shdValue <= 38) {
                shd.setVal(STShd.Enum.forInt(underLineStyle));
            }
            if (shdColor != null) {
                shd.setColor(shdColor);
            }
        }
    }

//    // 设置页面背景色
//    public void setDocumentbackground(XWPFDocument document, String bgColor) {
//        CTBackground bg = null;
//        if( document.getDocument().isSetBackground()){
//            bg = document.getDocument().getBackground();
//        }else{
//            bg = document.getDocument().addNewBackground();
//            }
//          bg.setColor(bgColor);
//    }

    public void addNewPage(XWPFDocument document, BreakType breakType) {
        XWPFParagraph xp = document.createParagraph();
        xp.createRun().addBreak(breakType);
    }

    public void saveDocument(XWPFDocument document, String savePath)
            throws Exception {
        FileOutputStream fos = new FileOutputStream(savePath);
        document.write(fos);
        fos.close();
    }
}