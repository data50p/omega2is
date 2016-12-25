package fpdo.sundry;

import java.awt.*;
import java.util.HashMap;


public class RGB {

    static class Item {
	int r, g, b;
	String name;

	Item(int r, int g, int b, String n) {
	    this.r = r;
	    this.g = g;
	    this.b = b;
	    this.name = n;
	}
    }

    static HashMap hm;
    static HashMap hmc;

    //	! $XConsortium: rgb.txt,v 10.41 94/02/20 18:39:36 rws Exp $
    static Item[] item = {
	    new Item(255, 250, 250, "snow"),
	    new Item(248, 248, 255, "ghost white"),
	    new Item(248, 248, 255, "GhostWhite"),
	    new Item(245, 245, 245, "white smoke"),
	    new Item(245, 245, 245, "WhiteSmoke"),
	    new Item(220, 220, 220, "gainsboro"),
	    new Item(255, 250, 240, "floral white"),
	    new Item(255, 250, 240, "FloralWhite"),
	    new Item(253, 245, 230, "old lace"),
	    new Item(253, 245, 230, "OldLace"),
	    new Item(250, 240, 230, "linen"),
	    new Item(250, 235, 215, "antique white"),
	    new Item(250, 235, 215, "AntiqueWhite"),
	    new Item(255, 239, 213, "papaya whip"),
	    new Item(255, 239, 213, "PapayaWhip"),
	    new Item(255, 235, 205, "blanched almond"),
	    new Item(255, 235, 205, "BlanchedAlmond"),
	    new Item(255, 228, 196, "bisque"),
	    new Item(255, 218, 185, "peach puff"),
	    new Item(255, 218, 185, "PeachPuff"),
	    new Item(255, 222, 173, "navajo white"),
	    new Item(255, 222, 173, "NavajoWhite"),
	    new Item(255, 228, 181, "moccasin"),
	    new Item(255, 248, 220, "cornsilk"),
	    new Item(255, 255, 240, "ivory"),
	    new Item(255, 250, 205, "lemon chiffon"),
	    new Item(255, 250, 205, "LemonChiffon"),
	    new Item(255, 245, 238, "seashell"),
	    new Item(240, 255, 240, "honeydew"),
	    new Item(245, 255, 250, "mint cream"),
	    new Item(245, 255, 250, "MintCream"),
	    new Item(240, 255, 255, "azure"),
	    new Item(240, 248, 255, "alice blue"),
	    new Item(240, 248, 255, "AliceBlue"),
	    new Item(230, 230, 250, "lavender"),
	    new Item(255, 240, 245, "lavender blush"),
	    new Item(255, 240, 245, "LavenderBlush"),
	    new Item(255, 228, 225, "misty rose"),
	    new Item(255, 228, 225, "MistyRose"),
	    new Item(255, 255, 255, "white"),
	    new Item(0, 0, 0, "black"),
	    new Item(47, 79, 79, "dark slate gray"),
	    new Item(47, 79, 79, "DarkSlateGray"),
	    new Item(47, 79, 79, "dark slate grey"),
	    new Item(47, 79, 79, "DarkSlateGrey"),
	    new Item(105, 105, 105, "dim gray"),
	    new Item(105, 105, 105, "DimGray"),
	    new Item(105, 105, 105, "dim grey"),
	    new Item(105, 105, 105, "DimGrey"),
	    new Item(112, 128, 144, "slate gray"),
	    new Item(112, 128, 144, "SlateGray"),
	    new Item(112, 128, 144, "slate grey"),
	    new Item(112, 128, 144, "SlateGrey"),
	    new Item(119, 136, 153, "light slate gray"),
	    new Item(119, 136, 153, "LightSlateGray"),
	    new Item(119, 136, 153, "light slate grey"),
	    new Item(119, 136, 153, "LightSlateGrey"),
	    new Item(190, 190, 190, "gray"),
	    new Item(190, 190, 190, "grey"),
	    new Item(211, 211, 211, "light grey"),
	    new Item(211, 211, 211, "LightGrey"),
	    new Item(211, 211, 211, "light gray"),
	    new Item(211, 211, 211, "LightGray"),
	    new Item(25, 25, 112, "midnight blue"),
	    new Item(25, 25, 112, "MidnightBlue"),
	    new Item(0, 0, 128, "navy"),
	    new Item(0, 0, 128, "navy blue"),
	    new Item(0, 0, 128, "NavyBlue"),
	    new Item(100, 149, 237, "cornflower blue"),
	    new Item(100, 149, 237, "CornflowerBlue"),
	    new Item(72, 61, 139, "dark slate blue"),
	    new Item(72, 61, 139, "DarkSlateBlue"),
	    new Item(106, 90, 205, "slate blue"),
	    new Item(106, 90, 205, "SlateBlue"),
	    new Item(123, 104, 238, "medium slate blue"),
	    new Item(123, 104, 238, "MediumSlateBlue"),
	    new Item(132, 112, 255, "light slate blue"),
	    new Item(132, 112, 255, "LightSlateBlue"),
	    new Item(0, 0, 205, "medium blue"),
	    new Item(0, 0, 205, "MediumBlue"),
	    new Item(65, 105, 225, "royal blue"),
	    new Item(65, 105, 225, "RoyalBlue"),
	    new Item(0, 0, 255, "blue"),
	    new Item(30, 144, 255, "dodger blue"),
	    new Item(30, 144, 255, "DodgerBlue"),
	    new Item(0, 191, 255, "deep sky blue"),
	    new Item(0, 191, 255, "DeepSkyBlue"),
	    new Item(135, 206, 235, "sky blue"),
	    new Item(135, 206, 235, "SkyBlue"),
	    new Item(135, 206, 250, "light sky blue"),
	    new Item(135, 206, 250, "LightSkyBlue"),
	    new Item(70, 130, 180, "steel blue"),
	    new Item(70, 130, 180, "SteelBlue"),
	    new Item(176, 196, 222, "light steel blue"),
	    new Item(176, 196, 222, "LightSteelBlue"),
	    new Item(173, 216, 230, "light blue"),
	    new Item(173, 216, 230, "LightBlue"),
	    new Item(176, 224, 230, "powder blue"),
	    new Item(176, 224, 230, "PowderBlue"),
	    new Item(175, 238, 238, "pale turquoise"),
	    new Item(175, 238, 238, "PaleTurquoise"),
	    new Item(0, 206, 209, "dark turquoise"),
	    new Item(0, 206, 209, "DarkTurquoise"),
	    new Item(72, 209, 204, "medium turquoise"),
	    new Item(72, 209, 204, "MediumTurquoise"),
	    new Item(64, 224, 208, "turquoise"),
	    new Item(0, 255, 255, "cyan"),
	    new Item(224, 255, 255, "light cyan"),
	    new Item(224, 255, 255, "LightCyan"),
	    new Item(95, 158, 160, "cadet blue"),
	    new Item(95, 158, 160, "CadetBlue"),
	    new Item(102, 205, 170, "medium aquamarine"),
	    new Item(102, 205, 170, "MediumAquamarine"),
	    new Item(127, 255, 212, "aquamarine"),
	    new Item(0, 100, 0, "dark green"),
	    new Item(0, 100, 0, "DarkGreen"),
	    new Item(85, 107, 47, "dark olive green"),
	    new Item(85, 107, 47, "DarkOliveGreen"),
	    new Item(143, 188, 143, "dark sea green"),
	    new Item(143, 188, 143, "DarkSeaGreen"),
	    new Item(46, 139, 87, "sea green"),
	    new Item(46, 139, 87, "SeaGreen"),
	    new Item(60, 179, 113, "medium sea green"),
	    new Item(60, 179, 113, "MediumSeaGreen"),
	    new Item(32, 178, 170, "light sea green"),
	    new Item(32, 178, 170, "LightSeaGreen"),
	    new Item(152, 251, 152, "pale green"),
	    new Item(152, 251, 152, "PaleGreen"),
	    new Item(0, 255, 127, "spring green"),
	    new Item(0, 255, 127, "SpringGreen"),
	    new Item(124, 252, 0, "lawn green"),
	    new Item(124, 252, 0, "LawnGreen"),
	    new Item(0, 255, 0, "green"),
	    new Item(127, 255, 0, "chartreuse"),
	    new Item(0, 250, 154, "medium spring green"),
	    new Item(0, 250, 154, "MediumSpringGreen"),
	    new Item(173, 255, 47, "green yellow"),
	    new Item(173, 255, 47, "GreenYellow"),
	    new Item(50, 205, 50, "lime green"),
	    new Item(50, 205, 50, "LimeGreen"),
	    new Item(154, 205, 50, "yellow green"),
	    new Item(154, 205, 50, "YellowGreen"),
	    new Item(34, 139, 34, "forest green"),
	    new Item(34, 139, 34, "ForestGreen"),
	    new Item(107, 142, 35, "olive drab"),
	    new Item(107, 142, 35, "OliveDrab"),
	    new Item(189, 183, 107, "dark khaki"),
	    new Item(189, 183, 107, "DarkKhaki"),
	    new Item(240, 230, 140, "khaki"),
	    new Item(238, 232, 170, "pale goldenrod"),
	    new Item(238, 232, 170, "PaleGoldenrod"),
	    new Item(250, 250, 210, "light goldenrod yellow"),
	    new Item(250, 250, 210, "LightGoldenrodYellow"),
	    new Item(255, 255, 224, "light yellow"),
	    new Item(255, 255, 224, "LightYellow"),
	    new Item(255, 255, 0, "yellow"),
	    new Item(255, 215, 0, "gold"),
	    new Item(238, 221, 130, "light goldenrod"),
	    new Item(238, 221, 130, "LightGoldenrod"),
	    new Item(218, 165, 32, "goldenrod"),
	    new Item(184, 134, 11, "dark goldenrod"),
	    new Item(184, 134, 11, "DarkGoldenrod"),
	    new Item(188, 143, 143, "rosy brown"),
	    new Item(188, 143, 143, "RosyBrown"),
	    new Item(205, 92, 92, "indian red"),
	    new Item(205, 92, 92, "IndianRed"),
	    new Item(139, 69, 19, "saddle brown"),
	    new Item(139, 69, 19, "SaddleBrown"),
	    new Item(160, 82, 45, "sienna"),
	    new Item(205, 133, 63, "peru"),
	    new Item(222, 184, 135, "burlywood"),
	    new Item(245, 245, 220, "beige"),
	    new Item(245, 222, 179, "wheat"),
	    new Item(244, 164, 96, "sandy brown"),
	    new Item(244, 164, 96, "SandyBrown"),
	    new Item(210, 180, 140, "tan"),
	    new Item(210, 105, 30, "chocolate"),
	    new Item(178, 34, 34, "firebrick"),
	    new Item(165, 42, 42, "brown"),
	    new Item(233, 150, 122, "dark salmon"),
	    new Item(233, 150, 122, "DarkSalmon"),
	    new Item(250, 128, 114, "salmon"),
	    new Item(255, 160, 122, "light salmon"),
	    new Item(255, 160, 122, "LightSalmon"),
	    new Item(255, 165, 0, "orange"),
	    new Item(255, 140, 0, "dark orange"),
	    new Item(255, 140, 0, "DarkOrange"),
	    new Item(255, 127, 80, "coral"),
	    new Item(240, 128, 128, "light coral"),
	    new Item(240, 128, 128, "LightCoral"),
	    new Item(255, 99, 71, "tomato"),
	    new Item(255, 69, 0, "orange red"),
	    new Item(255, 69, 0, "OrangeRed"),
	    new Item(255, 0, 0, "red"),
	    new Item(255, 105, 180, "hot pink"),
	    new Item(255, 105, 180, "HotPink"),
	    new Item(255, 20, 147, "deep pink"),
	    new Item(255, 20, 147, "DeepPink"),
	    new Item(255, 192, 203, "pink"),
	    new Item(255, 182, 193, "light pink"),
	    new Item(255, 182, 193, "LightPink"),
	    new Item(219, 112, 147, "pale violet red"),
	    new Item(219, 112, 147, "PaleVioletRed"),
	    new Item(176, 48, 96, "maroon"),
	    new Item(199, 21, 133, "medium violet red"),
	    new Item(199, 21, 133, "MediumVioletRed"),
	    new Item(208, 32, 144, "violet red"),
	    new Item(208, 32, 144, "VioletRed"),
	    new Item(255, 0, 255, "magenta"),
	    new Item(238, 130, 238, "violet"),
	    new Item(221, 160, 221, "plum"),
	    new Item(218, 112, 214, "orchid"),
	    new Item(186, 85, 211, "medium orchid"),
	    new Item(186, 85, 211, "MediumOrchid"),
	    new Item(153, 50, 204, "dark orchid"),
	    new Item(153, 50, 204, "DarkOrchid"),
	    new Item(148, 0, 211, "dark violet"),
	    new Item(148, 0, 211, "DarkViolet"),
	    new Item(138, 43, 226, "blue violet"),
	    new Item(138, 43, 226, "BlueViolet"),
	    new Item(160, 32, 240, "purple"),
	    new Item(147, 112, 219, "medium purple"),
	    new Item(147, 112, 219, "MediumPurple"),
	    new Item(216, 191, 216, "thistle"),
	    new Item(255, 250, 250, "snow1"),
	    new Item(238, 233, 233, "snow2"),
	    new Item(205, 201, 201, "snow3"),
	    new Item(139, 137, 137, "snow4"),
	    new Item(255, 245, 238, "seashell1"),
	    new Item(238, 229, 222, "seashell2"),
	    new Item(205, 197, 191, "seashell3"),
	    new Item(139, 134, 130, "seashell4"),
	    new Item(255, 239, 219, "AntiqueWhite1"),
	    new Item(238, 223, 204, "AntiqueWhite2"),
	    new Item(205, 192, 176, "AntiqueWhite3"),
	    new Item(139, 131, 120, "AntiqueWhite4"),
	    new Item(255, 228, 196, "bisque1"),
	    new Item(238, 213, 183, "bisque2"),
	    new Item(205, 183, 158, "bisque3"),
	    new Item(139, 125, 107, "bisque4"),
	    new Item(255, 218, 185, "PeachPuff1"),
	    new Item(238, 203, 173, "PeachPuff2"),
	    new Item(205, 175, 149, "PeachPuff3"),
	    new Item(139, 119, 101, "PeachPuff4"),
	    new Item(255, 222, 173, "NavajoWhite1"),
	    new Item(238, 207, 161, "NavajoWhite2"),
	    new Item(205, 179, 139, "NavajoWhite3"),
	    new Item(139, 121, 94, "NavajoWhite4"),
	    new Item(255, 250, 205, "LemonChiffon1"),
	    new Item(238, 233, 191, "LemonChiffon2"),
	    new Item(205, 201, 165, "LemonChiffon3"),
	    new Item(139, 137, 112, "LemonChiffon4"),
	    new Item(255, 248, 220, "cornsilk1"),
	    new Item(238, 232, 205, "cornsilk2"),
	    new Item(205, 200, 177, "cornsilk3"),
	    new Item(139, 136, 120, "cornsilk4"),
	    new Item(255, 255, 240, "ivory1"),
	    new Item(238, 238, 224, "ivory2"),
	    new Item(205, 205, 193, "ivory3"),
	    new Item(139, 139, 131, "ivory4"),
	    new Item(240, 255, 240, "honeydew1"),
	    new Item(224, 238, 224, "honeydew2"),
	    new Item(193, 205, 193, "honeydew3"),
	    new Item(131, 139, 131, "honeydew4"),
	    new Item(255, 240, 245, "LavenderBlush1"),
	    new Item(238, 224, 229, "LavenderBlush2"),
	    new Item(205, 193, 197, "LavenderBlush3"),
	    new Item(139, 131, 134, "LavenderBlush4"),
	    new Item(255, 228, 225, "MistyRose1"),
	    new Item(238, 213, 210, "MistyRose2"),
	    new Item(205, 183, 181, "MistyRose3"),
	    new Item(139, 125, 123, "MistyRose4"),
	    new Item(240, 255, 255, "azure1"),
	    new Item(224, 238, 238, "azure2"),
	    new Item(193, 205, 205, "azure3"),
	    new Item(131, 139, 139, "azure4"),
	    new Item(131, 111, 255, "SlateBlue1"),
	    new Item(122, 103, 238, "SlateBlue2"),
	    new Item(105, 89, 205, "SlateBlue3"),
	    new Item(71, 60, 139, "SlateBlue4"),
	    new Item(72, 118, 255, "RoyalBlue1"),
	    new Item(67, 110, 238, "RoyalBlue2"),
	    new Item(58, 95, 205, "RoyalBlue3"),
	    new Item(39, 64, 139, "RoyalBlue4"),
	    new Item(0, 0, 255, "blue1"),
	    new Item(0, 0, 238, "blue2"),
	    new Item(0, 0, 205, "blue3"),
	    new Item(0, 0, 139, "blue4"),
	    new Item(30, 144, 255, "DodgerBlue1"),
	    new Item(28, 134, 238, "DodgerBlue2"),
	    new Item(24, 116, 205, "DodgerBlue3"),
	    new Item(16, 78, 139, "DodgerBlue4"),
	    new Item(99, 184, 255, "SteelBlue1"),
	    new Item(92, 172, 238, "SteelBlue2"),
	    new Item(79, 148, 205, "SteelBlue3"),
	    new Item(54, 100, 139, "SteelBlue4"),
	    new Item(0, 191, 255, "DeepSkyBlue1"),
	    new Item(0, 178, 238, "DeepSkyBlue2"),
	    new Item(0, 154, 205, "DeepSkyBlue3"),
	    new Item(0, 104, 139, "DeepSkyBlue4"),
	    new Item(135, 206, 255, "SkyBlue1"),
	    new Item(126, 192, 238, "SkyBlue2"),
	    new Item(108, 166, 205, "SkyBlue3"),
	    new Item(74, 112, 139, "SkyBlue4"),
	    new Item(176, 226, 255, "LightSkyBlue1"),
	    new Item(164, 211, 238, "LightSkyBlue2"),
	    new Item(141, 182, 205, "LightSkyBlue3"),
	    new Item(96, 123, 139, "LightSkyBlue4"),
	    new Item(198, 226, 255, "SlateGray1"),
	    new Item(185, 211, 238, "SlateGray2"),
	    new Item(159, 182, 205, "SlateGray3"),
	    new Item(108, 123, 139, "SlateGray4"),
	    new Item(202, 225, 255, "LightSteelBlue1"),
	    new Item(188, 210, 238, "LightSteelBlue2"),
	    new Item(162, 181, 205, "LightSteelBlue3"),
	    new Item(110, 123, 139, "LightSteelBlue4"),
	    new Item(191, 239, 255, "LightBlue1"),
	    new Item(178, 223, 238, "LightBlue2"),
	    new Item(154, 192, 205, "LightBlue3"),
	    new Item(104, 131, 139, "LightBlue4"),
	    new Item(224, 255, 255, "LightCyan1"),
	    new Item(209, 238, 238, "LightCyan2"),
	    new Item(180, 205, 205, "LightCyan3"),
	    new Item(122, 139, 139, "LightCyan4"),
	    new Item(187, 255, 255, "PaleTurquoise1"),
	    new Item(174, 238, 238, "PaleTurquoise2"),
	    new Item(150, 205, 205, "PaleTurquoise3"),
	    new Item(102, 139, 139, "PaleTurquoise4"),
	    new Item(152, 245, 255, "CadetBlue1"),
	    new Item(142, 229, 238, "CadetBlue2"),
	    new Item(122, 197, 205, "CadetBlue3"),
	    new Item(83, 134, 139, "CadetBlue4"),
	    new Item(0, 245, 255, "turquoise1"),
	    new Item(0, 229, 238, "turquoise2"),
	    new Item(0, 197, 205, "turquoise3"),
	    new Item(0, 134, 139, "turquoise4"),
	    new Item(0, 255, 255, "cyan1"),
	    new Item(0, 238, 238, "cyan2"),
	    new Item(0, 205, 205, "cyan3"),
	    new Item(0, 139, 139, "cyan4"),
	    new Item(151, 255, 255, "DarkSlateGray1"),
	    new Item(141, 238, 238, "DarkSlateGray2"),
	    new Item(121, 205, 205, "DarkSlateGray3"),
	    new Item(82, 139, 139, "DarkSlateGray4"),
	    new Item(127, 255, 212, "aquamarine1"),
	    new Item(118, 238, 198, "aquamarine2"),
	    new Item(102, 205, 170, "aquamarine3"),
	    new Item(69, 139, 116, "aquamarine4"),
	    new Item(193, 255, 193, "DarkSeaGreen1"),
	    new Item(180, 238, 180, "DarkSeaGreen2"),
	    new Item(155, 205, 155, "DarkSeaGreen3"),
	    new Item(105, 139, 105, "DarkSeaGreen4"),
	    new Item(84, 255, 159, "SeaGreen1"),
	    new Item(78, 238, 148, "SeaGreen2"),
	    new Item(67, 205, 128, "SeaGreen3"),
	    new Item(46, 139, 87, "SeaGreen4"),
	    new Item(154, 255, 154, "PaleGreen1"),
	    new Item(144, 238, 144, "PaleGreen2"),
	    new Item(124, 205, 124, "PaleGreen3"),
	    new Item(84, 139, 84, "PaleGreen4"),
	    new Item(0, 255, 127, "SpringGreen1"),
	    new Item(0, 238, 118, "SpringGreen2"),
	    new Item(0, 205, 102, "SpringGreen3"),
	    new Item(0, 139, 69, "SpringGreen4"),
	    new Item(0, 255, 0, "green1"),
	    new Item(0, 238, 0, "green2"),
	    new Item(0, 205, 0, "green3"),
	    new Item(0, 139, 0, "green4"),
	    new Item(127, 255, 0, "chartreuse1"),
	    new Item(118, 238, 0, "chartreuse2"),
	    new Item(102, 205, 0, "chartreuse3"),
	    new Item(69, 139, 0, "chartreuse4"),
	    new Item(192, 255, 62, "OliveDrab1"),
	    new Item(179, 238, 58, "OliveDrab2"),
	    new Item(154, 205, 50, "OliveDrab3"),
	    new Item(105, 139, 34, "OliveDrab4"),
	    new Item(202, 255, 112, "DarkOliveGreen1"),
	    new Item(188, 238, 104, "DarkOliveGreen2"),
	    new Item(162, 205, 90, "DarkOliveGreen3"),
	    new Item(110, 139, 61, "DarkOliveGreen4"),
	    new Item(255, 246, 143, "khaki1"),
	    new Item(238, 230, 133, "khaki2"),
	    new Item(205, 198, 115, "khaki3"),
	    new Item(139, 134, 78, "khaki4"),
	    new Item(255, 236, 139, "LightGoldenrod1"),
	    new Item(238, 220, 130, "LightGoldenrod2"),
	    new Item(205, 190, 112, "LightGoldenrod3"),
	    new Item(139, 129, 76, "LightGoldenrod4"),
	    new Item(255, 255, 224, "LightYellow1"),
	    new Item(238, 238, 209, "LightYellow2"),
	    new Item(205, 205, 180, "LightYellow3"),
	    new Item(139, 139, 122, "LightYellow4"),
	    new Item(255, 255, 0, "yellow1"),
	    new Item(238, 238, 0, "yellow2"),
	    new Item(205, 205, 0, "yellow3"),
	    new Item(139, 139, 0, "yellow4"),
	    new Item(255, 215, 0, "gold1"),
	    new Item(238, 201, 0, "gold2"),
	    new Item(205, 173, 0, "gold3"),
	    new Item(139, 117, 0, "gold4"),
	    new Item(255, 193, 37, "goldenrod1"),
	    new Item(238, 180, 34, "goldenrod2"),
	    new Item(205, 155, 29, "goldenrod3"),
	    new Item(139, 105, 20, "goldenrod4"),
	    new Item(255, 185, 15, "DarkGoldenrod1"),
	    new Item(238, 173, 14, "DarkGoldenrod2"),
	    new Item(205, 149, 12, "DarkGoldenrod3"),
	    new Item(139, 101, 8, "DarkGoldenrod4"),
	    new Item(255, 193, 193, "RosyBrown1"),
	    new Item(238, 180, 180, "RosyBrown2"),
	    new Item(205, 155, 155, "RosyBrown3"),
	    new Item(139, 105, 105, "RosyBrown4"),
	    new Item(255, 106, 106, "IndianRed1"),
	    new Item(238, 99, 99, "IndianRed2"),
	    new Item(205, 85, 85, "IndianRed3"),
	    new Item(139, 58, 58, "IndianRed4"),
	    new Item(255, 130, 71, "sienna1"),
	    new Item(238, 121, 66, "sienna2"),
	    new Item(205, 104, 57, "sienna3"),
	    new Item(139, 71, 38, "sienna4"),
	    new Item(255, 211, 155, "burlywood1"),
	    new Item(238, 197, 145, "burlywood2"),
	    new Item(205, 170, 125, "burlywood3"),
	    new Item(139, 115, 85, "burlywood4"),
	    new Item(255, 231, 186, "wheat1"),
	    new Item(238, 216, 174, "wheat2"),
	    new Item(205, 186, 150, "wheat3"),
	    new Item(139, 126, 102, "wheat4"),
	    new Item(255, 165, 79, "tan1"),
	    new Item(238, 154, 73, "tan2"),
	    new Item(205, 133, 63, "tan3"),
	    new Item(139, 90, 43, "tan4"),
	    new Item(255, 127, 36, "chocolate1"),
	    new Item(238, 118, 33, "chocolate2"),
	    new Item(205, 102, 29, "chocolate3"),
	    new Item(139, 69, 19, "chocolate4"),
	    new Item(255, 48, 48, "firebrick1"),
	    new Item(238, 44, 44, "firebrick2"),
	    new Item(205, 38, 38, "firebrick3"),
	    new Item(139, 26, 26, "firebrick4"),
	    new Item(255, 64, 64, "brown1"),
	    new Item(238, 59, 59, "brown2"),
	    new Item(205, 51, 51, "brown3"),
	    new Item(139, 35, 35, "brown4"),
	    new Item(255, 140, 105, "salmon1"),
	    new Item(238, 130, 98, "salmon2"),
	    new Item(205, 112, 84, "salmon3"),
	    new Item(139, 76, 57, "salmon4"),
	    new Item(255, 160, 122, "LightSalmon1"),
	    new Item(238, 149, 114, "LightSalmon2"),
	    new Item(205, 129, 98, "LightSalmon3"),
	    new Item(139, 87, 66, "LightSalmon4"),
	    new Item(255, 165, 0, "orange1"),
	    new Item(238, 154, 0, "orange2"),
	    new Item(205, 133, 0, "orange3"),
	    new Item(139, 90, 0, "orange4"),
	    new Item(255, 127, 0, "DarkOrange1"),
	    new Item(238, 118, 0, "DarkOrange2"),
	    new Item(205, 102, 0, "DarkOrange3"),
	    new Item(139, 69, 0, "DarkOrange4"),
	    new Item(255, 114, 86, "coral1"),
	    new Item(238, 106, 80, "coral2"),
	    new Item(205, 91, 69, "coral3"),
	    new Item(139, 62, 47, "coral4"),
	    new Item(255, 99, 71, "tomato1"),
	    new Item(238, 92, 66, "tomato2"),
	    new Item(205, 79, 57, "tomato3"),
	    new Item(139, 54, 38, "tomato4"),
	    new Item(255, 69, 0, "OrangeRed1"),
	    new Item(238, 64, 0, "OrangeRed2"),
	    new Item(205, 55, 0, "OrangeRed3"),
	    new Item(139, 37, 0, "OrangeRed4"),
	    new Item(255, 0, 0, "red1"),
	    new Item(238, 0, 0, "red2"),
	    new Item(205, 0, 0, "red3"),
	    new Item(139, 0, 0, "red4"),
	    new Item(255, 20, 147, "DeepPink1"),
	    new Item(238, 18, 137, "DeepPink2"),
	    new Item(205, 16, 118, "DeepPink3"),
	    new Item(139, 10, 80, "DeepPink4"),
	    new Item(255, 110, 180, "HotPink1"),
	    new Item(238, 106, 167, "HotPink2"),
	    new Item(205, 96, 144, "HotPink3"),
	    new Item(139, 58, 98, "HotPink4"),
	    new Item(255, 181, 197, "pink1"),
	    new Item(238, 169, 184, "pink2"),
	    new Item(205, 145, 158, "pink3"),
	    new Item(139, 99, 108, "pink4"),
	    new Item(255, 174, 185, "LightPink1"),
	    new Item(238, 162, 173, "LightPink2"),
	    new Item(205, 140, 149, "LightPink3"),
	    new Item(139, 95, 101, "LightPink4"),
	    new Item(255, 130, 171, "PaleVioletRed1"),
	    new Item(238, 121, 159, "PaleVioletRed2"),
	    new Item(205, 104, 137, "PaleVioletRed3"),
	    new Item(139, 71, 93, "PaleVioletRed4"),
	    new Item(255, 52, 179, "maroon1"),
	    new Item(238, 48, 167, "maroon2"),
	    new Item(205, 41, 144, "maroon3"),
	    new Item(139, 28, 98, "maroon4"),
	    new Item(255, 62, 150, "VioletRed1"),
	    new Item(238, 58, 140, "VioletRed2"),
	    new Item(205, 50, 120, "VioletRed3"),
	    new Item(139, 34, 82, "VioletRed4"),
	    new Item(255, 0, 255, "magenta1"),
	    new Item(238, 0, 238, "magenta2"),
	    new Item(205, 0, 205, "magenta3"),
	    new Item(139, 0, 139, "magenta4"),
	    new Item(255, 131, 250, "orchid1"),
	    new Item(238, 122, 233, "orchid2"),
	    new Item(205, 105, 201, "orchid3"),
	    new Item(139, 71, 137, "orchid4"),
	    new Item(255, 187, 255, "plum1"),
	    new Item(238, 174, 238, "plum2"),
	    new Item(205, 150, 205, "plum3"),
	    new Item(139, 102, 139, "plum4"),
	    new Item(224, 102, 255, "MediumOrchid1"),
	    new Item(209, 95, 238, "MediumOrchid2"),
	    new Item(180, 82, 205, "MediumOrchid3"),
	    new Item(122, 55, 139, "MediumOrchid4"),
	    new Item(191, 62, 255, "DarkOrchid1"),
	    new Item(178, 58, 238, "DarkOrchid2"),
	    new Item(154, 50, 205, "DarkOrchid3"),
	    new Item(104, 34, 139, "DarkOrchid4"),
	    new Item(155, 48, 255, "purple1"),
	    new Item(145, 44, 238, "purple2"),
	    new Item(125, 38, 205, "purple3"),
	    new Item(85, 26, 139, "purple4"),
	    new Item(171, 130, 255, "MediumPurple1"),
	    new Item(159, 121, 238, "MediumPurple2"),
	    new Item(137, 104, 205, "MediumPurple3"),
	    new Item(93, 71, 139, "MediumPurple4"),
	    new Item(255, 225, 255, "thistle1"),
	    new Item(238, 210, 238, "thistle2"),
	    new Item(205, 181, 205, "thistle3"),
	    new Item(139, 123, 139, "thistle4"),
	    new Item(0, 0, 0, "gray0"),
	    new Item(0, 0, 0, "grey0"),
	    new Item(3, 3, 3, "gray1"),
	    new Item(3, 3, 3, "grey1"),
	    new Item(5, 5, 5, "gray2"),
	    new Item(5, 5, 5, "grey2"),
	    new Item(8, 8, 8, "gray3"),
	    new Item(8, 8, 8, "grey3"),
	    new Item(10, 10, 10, "gray4"),
	    new Item(10, 10, 10, "grey4"),
	    new Item(13, 13, 13, "gray5"),
	    new Item(13, 13, 13, "grey5"),
	    new Item(15, 15, 15, "gray6"),
	    new Item(15, 15, 15, "grey6"),
	    new Item(18, 18, 18, "gray7"),
	    new Item(18, 18, 18, "grey7"),
	    new Item(20, 20, 20, "gray8"),
	    new Item(20, 20, 20, "grey8"),
	    new Item(23, 23, 23, "gray9"),
	    new Item(23, 23, 23, "grey9"),
	    new Item(26, 26, 26, "gray10"),
	    new Item(26, 26, 26, "grey10"),
	    new Item(28, 28, 28, "gray11"),
	    new Item(28, 28, 28, "grey11"),
	    new Item(31, 31, 31, "gray12"),
	    new Item(31, 31, 31, "grey12"),
	    new Item(33, 33, 33, "gray13"),
	    new Item(33, 33, 33, "grey13"),
	    new Item(36, 36, 36, "gray14"),
	    new Item(36, 36, 36, "grey14"),
	    new Item(38, 38, 38, "gray15"),
	    new Item(38, 38, 38, "grey15"),
	    new Item(41, 41, 41, "gray16"),
	    new Item(41, 41, 41, "grey16"),
	    new Item(43, 43, 43, "gray17"),
	    new Item(43, 43, 43, "grey17"),
	    new Item(46, 46, 46, "gray18"),
	    new Item(46, 46, 46, "grey18"),
	    new Item(48, 48, 48, "gray19"),
	    new Item(48, 48, 48, "grey19"),
	    new Item(51, 51, 51, "gray20"),
	    new Item(51, 51, 51, "grey20"),
	    new Item(54, 54, 54, "gray21"),
	    new Item(54, 54, 54, "grey21"),
	    new Item(56, 56, 56, "gray22"),
	    new Item(56, 56, 56, "grey22"),
	    new Item(59, 59, 59, "gray23"),
	    new Item(59, 59, 59, "grey23"),
	    new Item(61, 61, 61, "gray24"),
	    new Item(61, 61, 61, "grey24"),
	    new Item(64, 64, 64, "gray25"),
	    new Item(64, 64, 64, "grey25"),
	    new Item(66, 66, 66, "gray26"),
	    new Item(66, 66, 66, "grey26"),
	    new Item(69, 69, 69, "gray27"),
	    new Item(69, 69, 69, "grey27"),
	    new Item(71, 71, 71, "gray28"),
	    new Item(71, 71, 71, "grey28"),
	    new Item(74, 74, 74, "gray29"),
	    new Item(74, 74, 74, "grey29"),
	    new Item(77, 77, 77, "gray30"),
	    new Item(77, 77, 77, "grey30"),
	    new Item(79, 79, 79, "gray31"),
	    new Item(79, 79, 79, "grey31"),
	    new Item(82, 82, 82, "gray32"),
	    new Item(82, 82, 82, "grey32"),
	    new Item(84, 84, 84, "gray33"),
	    new Item(84, 84, 84, "grey33"),
	    new Item(87, 87, 87, "gray34"),
	    new Item(87, 87, 87, "grey34"),
	    new Item(89, 89, 89, "gray35"),
	    new Item(89, 89, 89, "grey35"),
	    new Item(92, 92, 92, "gray36"),
	    new Item(92, 92, 92, "grey36"),
	    new Item(94, 94, 94, "gray37"),
	    new Item(94, 94, 94, "grey37"),
	    new Item(97, 97, 97, "gray38"),
	    new Item(97, 97, 97, "grey38"),
	    new Item(99, 99, 99, "gray39"),
	    new Item(99, 99, 99, "grey39"),
	    new Item(102, 102, 102, "gray40"),
	    new Item(102, 102, 102, "grey40"),
	    new Item(105, 105, 105, "gray41"),
	    new Item(105, 105, 105, "grey41"),
	    new Item(107, 107, 107, "gray42"),
	    new Item(107, 107, 107, "grey42"),
	    new Item(110, 110, 110, "gray43"),
	    new Item(110, 110, 110, "grey43"),
	    new Item(112, 112, 112, "gray44"),
	    new Item(112, 112, 112, "grey44"),
	    new Item(115, 115, 115, "gray45"),
	    new Item(115, 115, 115, "grey45"),
	    new Item(117, 117, 117, "gray46"),
	    new Item(117, 117, 117, "grey46"),
	    new Item(120, 120, 120, "gray47"),
	    new Item(120, 120, 120, "grey47"),
	    new Item(122, 122, 122, "gray48"),
	    new Item(122, 122, 122, "grey48"),
	    new Item(125, 125, 125, "gray49"),
	    new Item(125, 125, 125, "grey49"),
	    new Item(127, 127, 127, "gray50"),
	    new Item(127, 127, 127, "grey50"),
	    new Item(130, 130, 130, "gray51"),
	    new Item(130, 130, 130, "grey51"),
	    new Item(133, 133, 133, "gray52"),
	    new Item(133, 133, 133, "grey52"),
	    new Item(135, 135, 135, "gray53"),
	    new Item(135, 135, 135, "grey53"),
	    new Item(138, 138, 138, "gray54"),
	    new Item(138, 138, 138, "grey54"),
	    new Item(140, 140, 140, "gray55"),
	    new Item(140, 140, 140, "grey55"),
	    new Item(143, 143, 143, "gray56"),
	    new Item(143, 143, 143, "grey56"),
	    new Item(145, 145, 145, "gray57"),
	    new Item(145, 145, 145, "grey57"),
	    new Item(148, 148, 148, "gray58"),
	    new Item(148, 148, 148, "grey58"),
	    new Item(150, 150, 150, "gray59"),
	    new Item(150, 150, 150, "grey59"),
	    new Item(153, 153, 153, "gray60"),
	    new Item(153, 153, 153, "grey60"),
	    new Item(156, 156, 156, "gray61"),
	    new Item(156, 156, 156, "grey61"),
	    new Item(158, 158, 158, "gray62"),
	    new Item(158, 158, 158, "grey62"),
	    new Item(161, 161, 161, "gray63"),
	    new Item(161, 161, 161, "grey63"),
	    new Item(163, 163, 163, "gray64"),
	    new Item(163, 163, 163, "grey64"),
	    new Item(166, 166, 166, "gray65"),
	    new Item(166, 166, 166, "grey65"),
	    new Item(168, 168, 168, "gray66"),
	    new Item(168, 168, 168, "grey66"),
	    new Item(171, 171, 171, "gray67"),
	    new Item(171, 171, 171, "grey67"),
	    new Item(173, 173, 173, "gray68"),
	    new Item(173, 173, 173, "grey68"),
	    new Item(176, 176, 176, "gray69"),
	    new Item(176, 176, 176, "grey69"),
	    new Item(179, 179, 179, "gray70"),
	    new Item(179, 179, 179, "grey70"),
	    new Item(181, 181, 181, "gray71"),
	    new Item(181, 181, 181, "grey71"),
	    new Item(184, 184, 184, "gray72"),
	    new Item(184, 184, 184, "grey72"),
	    new Item(186, 186, 186, "gray73"),
	    new Item(186, 186, 186, "grey73"),
	    new Item(189, 189, 189, "gray74"),
	    new Item(189, 189, 189, "grey74"),
	    new Item(191, 191, 191, "gray75"),
	    new Item(191, 191, 191, "grey75"),
	    new Item(194, 194, 194, "gray76"),
	    new Item(194, 194, 194, "grey76"),
	    new Item(196, 196, 196, "gray77"),
	    new Item(196, 196, 196, "grey77"),
	    new Item(199, 199, 199, "gray78"),
	    new Item(199, 199, 199, "grey78"),
	    new Item(201, 201, 201, "gray79"),
	    new Item(201, 201, 201, "grey79"),
	    new Item(204, 204, 204, "gray80"),
	    new Item(204, 204, 204, "grey80"),
	    new Item(207, 207, 207, "gray81"),
	    new Item(207, 207, 207, "grey81"),
	    new Item(209, 209, 209, "gray82"),
	    new Item(209, 209, 209, "grey82"),
	    new Item(212, 212, 212, "gray83"),
	    new Item(212, 212, 212, "grey83"),
	    new Item(214, 214, 214, "gray84"),
	    new Item(214, 214, 214, "grey84"),
	    new Item(217, 217, 217, "gray85"),
	    new Item(217, 217, 217, "grey85"),
	    new Item(219, 219, 219, "gray86"),
	    new Item(219, 219, 219, "grey86"),
	    new Item(222, 222, 222, "gray87"),
	    new Item(222, 222, 222, "grey87"),
	    new Item(224, 224, 224, "gray88"),
	    new Item(224, 224, 224, "grey88"),
	    new Item(227, 227, 227, "gray89"),
	    new Item(227, 227, 227, "grey89"),
	    new Item(229, 229, 229, "gray90"),
	    new Item(229, 229, 229, "grey90"),
	    new Item(232, 232, 232, "gray91"),
	    new Item(232, 232, 232, "grey91"),
	    new Item(235, 235, 235, "gray92"),
	    new Item(235, 235, 235, "grey92"),
	    new Item(237, 237, 237, "gray93"),
	    new Item(237, 237, 237, "grey93"),
	    new Item(240, 240, 240, "gray94"),
	    new Item(240, 240, 240, "grey94"),
	    new Item(242, 242, 242, "gray95"),
	    new Item(242, 242, 242, "grey95"),
	    new Item(245, 245, 245, "gray96"),
	    new Item(245, 245, 245, "grey96"),
	    new Item(247, 247, 247, "gray97"),
	    new Item(247, 247, 247, "grey97"),
	    new Item(250, 250, 250, "gray98"),
	    new Item(250, 250, 250, "grey98"),
	    new Item(252, 252, 252, "gray99"),
	    new Item(252, 252, 252, "grey99"),
	    new Item(255, 255, 255, "gray100"),
	    new Item(255, 255, 255, "grey100"),
	    new Item(169, 169, 169, "dark grey"),
	    new Item(169, 169, 169, "DarkGrey"),
	    new Item(169, 169, 169, "dark gray"),
	    new Item(169, 169, 169, "DarkGray"),
	    new Item(0, 0, 139, "dark blue"),
	    new Item(0, 0, 139, "DarkBlue"),
	    new Item(0, 139, 139, "dark cyan"),
	    new Item(0, 139, 139, "DarkCyan"),
	    new Item(139, 0, 139, "dark magenta"),
	    new Item(139, 0, 139, "DarkMagenta"),
	    new Item(139, 0, 0, "dark red"),
	    new Item(139, 0, 0, "DarkRed"),
	    new Item(144, 238, 144, "light green"),
	    new Item(144, 238, 144, "LightGreen")
    };

    public static Color getColor(String name) {
	return getColor(name, Color.black);
    }

    public static Color getColor(String name, Color def) {
	if (hm == null) {
	    hm = new HashMap();
	    hmc = new HashMap();
	    for (int i = 0; i < item.length; i++)
		hm.put(item[i].name, new Integer(i));
	}
	Color col = (Color) hmc.get(name);
	if (col == null) {
	    Integer Ix = (Integer) hm.get(name);
	    if (Ix == null)
		return def;
	    int ix = Ix.intValue();
	    col = new Color(item[ix].r,
		    item[ix].g,
		    item[ix].b);
	    hmc.put(name, col);
	}
	return col;
    }
}
