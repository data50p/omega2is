package com.femtioprocent.omega.media.audio.impl;

import com.femtioprocent.omega.util.Log;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by lars on 2017-07-09.
 *
 *
 * On DOS
 *
 * PowerShell -Command "Add-Type –AssemblyName System.Speech; (New-Object System.Speech.Synthesis.SpeechSynthesizer).Speak('hello');
 *
 */
public class TTS_mac {

    private String[][] voice_lang = {
            {"Alex", "en_US"},
            {"Alice", "it_IT"},
            {"Alva", "sv_SE"},
            {"Amelie", "fr_CA"},
            {"Anna", "de_DE"},
            {"Carmit", "he_IL"},
            {"Damayanti", "id_ID"},
            {"Daniel", "en_GB"},
            {"Diego", "es_AR"},
            {"Ellen", "nl_BE"},
            {"Fiona", "en-scotland"},
            {"Fred", "en_US"},
            {"Ioana", "ro_RO"},
            {"Joana", "pt_PT"},
            {"Jorge", "es_ES"},
            {"Juan", "es_MX"},
            {"Kanya", "th_TH"},
            {"Karen", "en_AU"},
            {"Kyoko", "ja_JP"},
            {"Laura", "sk_SK"},
            {"Lekha", "hi_IN"},
            {"Luca", "it_IT"},
            {"Luciana", "pt_BR"},
            {"Maged", "ar_SA"},
            {"Mariska", "hu_HU"},
            {"Mei-Jia", "zh_TW"},
            {"Melina", "el_GR"},
            {"Milena", "ru_RU"},
            {"Moira", "en_IE"},
            {"Monica", "es_ES"},
            {"Nora", "nb_NO"},
            {"Paulina", "es_MX"},
            {"Samantha", "en_US"},
            {"Sara", "da_DK"},
            {"Satu", "fi_FI"},
            {"Sin-ji", "zh_HK"},
            {"Tessa", "en_ZA"},
            {"Thomas", "fr_FR"},
            {"Ting-Ting", "zh_CN"},
            {"Veena", "en_IN"},
            {"Victoria", "en_US"},
            {"Xander", "nl_NL"},
            {"Yelda", "tr_TR"},
            {"Yuna", "ko_KR"},
            {"Yuri", "ru_RU"},
            {"Zosia", "pl_PL"},
            {"Zuzana", "cs_CZ"},
    };

    HashMap<String,String> map = new HashMap<>();

    public boolean say(String lang, String s, boolean wait) {
        if ( map.size() == 0 ) {
            for(String[] sa : voice_lang) {
                map.put(sa[1], sa[0]);
                int ix = sa[1].indexOf("_");
                if ( ix != -1 )
                    if ( map.get(sa[1].substring(0, ix)) == null )
                        map.put(sa[1].substring(0, ix), sa[0]);
            }
        }
        String v = map.get(lang);
        ProcessBuilder pb;
        pb = v == null ? new ProcessBuilder("say", s) : new ProcessBuilder("say", "-v", v, s);
        try {
            Log.getLogger().info("TTS: " + v + ' ' + lang + ' ' + s);
            Process p = pb.start();
            if ( wait )
                p.waitFor();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
