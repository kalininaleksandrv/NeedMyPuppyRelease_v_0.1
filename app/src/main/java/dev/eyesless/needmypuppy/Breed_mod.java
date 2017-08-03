package dev.eyesless.needmypuppy;

import java.io.Serializable;

/**
 * Created by Eyesless on 19.06.2017.
 */

public class Breed_mod implements Serializable {

    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_description() {
        return b_description;
    }

    public void setB_description(String b_description) {
        this.b_description = b_description;
    }

    public String getB_image_res_id() {
        return b_image_res_id;
    }

    public String getB_image_fs_res_id() {
        return b_image_fs_res_id;
    }

    public void setB_image_res_id(String b_image_res_id) {
        this.b_image_res_id = b_image_res_id;
    }

    public void setB_image_fs_res_id(String b_image_res_id) {
        this.b_image_fs_res_id = b_image_res_id;
    }

    public int getB_size() {
        return b_size;
    }

    public void setB_size(int b_size) {
        this.b_size = b_size;
    }

    public String getB_description_full() {return b_description_full; }

    public void setB_description_full(String b_description_full) {this.b_description_full = b_description_full; }

    public int getB_obidience() {
        return b_obidience;
    }

    public void setB_obidience(int b_obidience) {
        this.b_obidience = b_obidience;
    }

    public int getB_guard() {
        return b_guard;
    }

    public void setB_guard(int b_guard) {
        this.b_guard = b_guard;
    }

    public int getB_agresssive() {
        return b_agresssive;
    }

    public void setB_agresssive(int b_agresssive) {
        this.b_agresssive = b_agresssive;
    }

    public int getB_active() {
        return b_active;
    }

    public void setB_active(int b_active) {
        this.b_active = b_active;
    }

    public int getB_hardy() {
        return b_hardy;
    }

    public void setB_hardy(int b_hardy) {
        this.b_hardy = b_hardy;
    }

    public int getB_care() {
        return b_care;
    }

    public void setB_care(int b_care) {
        this.b_care = b_care;
    }

    public String getB_hunt() {
        return b_hunt;
    }

    public void setB_hunt(String b_hunt) {
        this.b_hunt = b_hunt;
    }

    public String getB_weblinc() {
        return b_weblinc;
    }

    public void setB_weblinc(String b_weblinc) {
        this.b_weblinc = b_weblinc;
    }

    public boolean isB_favor() {
        return b_favor;
    }

    public void setB_favor(boolean b_favor) {
        this.b_favor = b_favor;
    }

    public String getB_comment() {
        return b_comment;
    }

    public void setB_comment(String b_comment) {
        this.b_comment = b_comment;
    }

    public int getB_idfci() {
        return b_idfci;
    }

    public void setB_idfci(int b_idfci) {
        this.b_idfci = b_idfci;
    }

    public String getB_hair() {
        return b_hair;
    }

    public void setB_hair(String b_hair) {
        this.b_hair = b_hair;
    }

    public String getB_blackorwhite() {
        return b_blackorwhite;
    }

    public void setB_blackorwhite(String b_blackorwhite) {
        this.b_blackorwhite = b_blackorwhite;
    }

    public String getB_noalergy() {
        return b_noalergy;
    }

    public void setB_noalergy(String b_noalergy) {
        this.b_noalergy = b_noalergy;
    }

    public String getB_weblinc_wiki() {
        return b_weblinc_wiki;
    }

    public void setB_weblinc_wiki(String b_weblinc_wiki) {
        this.b_weblinc_wiki = b_weblinc_wiki;
    }


    private String b_title;
    private String b_description;
    private String b_description_full;
    private String b_image_res_id;
    private String b_image_fs_res_id;
    private int b_obidience;
    private int b_guard;
    private int b_agresssive;
    private int b_active;
    private int b_hardy;
    private int b_size;
    private int b_care;
    private String b_hunt;
    private String b_weblinc;
    private String b_weblinc_wiki;
    private int b_idfci;
    private String b_hair;
    private String b_blackorwhite;
    private String b_noalergy;
    private boolean b_favor;
    private String b_comment;
}
