package com.ahu.coding.beast.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class MenuVo {
    /**
     * 当前菜单id
     */
    private Long id;
    /**
     * 父菜单id
     */
    private Long pId;
    /**
     * 菜单名称
     */
    private String name;
    private Integer rank = 0;
    private List<MenuVo> subMenus = new ArrayList<>();

    public MenuVo(Long id, Long pId) {
        this.id = id;
        this.pId = pId;
    }
}

