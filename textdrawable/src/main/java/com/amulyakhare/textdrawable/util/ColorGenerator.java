package com.amulyakhare.textdrawable.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Reconstruction of amulyakhare/TextDrawable's ColorGenerator utility
 * (MIT licensed, https://github.com/amulyakhare/TextDrawable). The
 * upstream "Import source from amulyakhare/TextDrawable" vendoring
 * commit (f304e40d57) only carried TextDrawable.java, not this class -
 * by the time that commit landed (June 2023) this project no longer
 * referenced ColorGenerator. It's needed here for
 * AttachmentTypeSelectorView.kt (ColorGenerator.MATERIAL / getColor).
 * The public API (class/field/method names) matches upstream; the exact
 * palette hex values are approximated from memory, not guaranteed
 * byte-for-byte identical to upstream.
 */
public class ColorGenerator {

    public static final ColorGenerator MATERIAL = create(new ArrayList<Integer>() {{
        add(0xffe57373);
        add(0xfff06292);
        add(0xffba68c8);
        add(0xff9575cd);
        add(0xff7986cb);
        add(0xff64b5f6);
        add(0xff4fc3f7);
        add(0xff4dd0e1);
        add(0xff4db6ac);
        add(0xff81c784);
        add(0xffaed581);
        add(0xffff8a65);
        add(0xffd4e157);
        add(0xffffd54f);
        add(0xffffb74d);
        add(0xffa1887f);
        add(0xff90a4ae);
    }});

    public static final ColorGenerator DEFAULT = create(new ArrayList<Integer>() {{
        add(0xff1abc9c);
        add(0xff2ecc71);
        add(0xff3498db);
        add(0xff9b59b6);
        add(0xff34495e);
        add(0xff16a085);
        add(0xff27ae60);
        add(0xff2980b9);
        add(0xff8e44ad);
        add(0xff2c3e50);
        add(0xfff1c40f);
        add(0xffe67e22);
        add(0xffe74c3c);
        add(0xffecf0f1);
        add(0xff95a5a6);
        add(0xfff39c12);
        add(0xffd35400);
        add(0xffc0392b);
        add(0xffbdc3c7);
        add(0xff7f8c8d);
    }});

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }
}
