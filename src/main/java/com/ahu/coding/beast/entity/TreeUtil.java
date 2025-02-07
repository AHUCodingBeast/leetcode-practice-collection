package com.ahu.coding.beast.entity;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 把具有父子关系的模型整理成一棵树的相关工具类
 * 适用场景：多级菜单、省市级区域数据
 */
public class TreeUtil {
    /**
     * @param allData        具有父子关系的元素列表
     * @param rootCheck      一个Predicate函数接口实例，用来判断某个元素是否是根节点
     * @param parentCheck    检查两个元素之间是否存在父子关系前一个元素是不是后一个元素的父节点
     * @param setSubChildren 设置子节点到父节点的方法
     * @param <E>            元素类型
     * @return 根节点集合《已经完成了树的建立，根节点下面已经有了孩子关系》
     */
    public static <E> List<E> makeTree(List<E> allData, Predicate<E> rootCheck, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> setSubChildren) {
        return allData.stream()
                // 过滤出来所有的根节点，对于菜单的场景，可能有多个根节点
                .filter(rootCheck)
                // 对每个根节点设置孩子节点
                .peek(x -> setSubChildren.accept(x, makeChildren(x, allData, parentCheck, setSubChildren)))
                .collect(Collectors.toList());
    }

    /**
     * 先序
     */
    public static <E> void forPreOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> getSubChildren) {
        for (E l : tree) {
            consumer.accept(l);
            List<E> es = getSubChildren.apply(l);
            if (es != null && !es.isEmpty()) {
                forPreOrder(es, consumer, getSubChildren);
            }
        }
    }

    /**
     * 层次遍历
     */
    public static <E> void forLevelOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> getSubChildren) {
        Queue<E> queue = new LinkedList<>(tree);
        while (!queue.isEmpty()) {
            E item = queue.poll();
            consumer.accept(item);
            List<E> childList = getSubChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                queue.addAll(childList);
            }
        }
    }

    /**
     * 后序
     */
    public static <E> void forPostOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> getSubChildren) {
        for (E item : tree) {
            List<E> childList = getSubChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                forPostOrder(childList, consumer, getSubChildren);
            }
            consumer.accept(item);
        }
    }

    /**
     * 平铺
     */
    public static <E> List<E> flat(List<E> tree, Function<E, List<E>> getSubChildren, Consumer<E> setSubChildren) {
        List<E> res = new ArrayList<>();
        forPostOrder(tree, item -> {
            setSubChildren.accept(item);
            res.add(item);
        }, getSubChildren);
        return res;
    }

    /**
     * 子节点排序
     */
    public static <E> List<E> sort(List<E> tree, Comparator<? super E> comparator, Function<E, List<E>> getChildren) {
        for (E item : tree) {
            List<E> childList = getChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                sort(childList, comparator, getChildren);
            }
        }
        tree.sort(comparator);
        return tree;
    }


    private static <E> List<E> makeChildren(E root, List<E> allData, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> setSubChildren) {

        //遍历所有数据，获取当前节点的子节点
        return allData.stream()
                // 找出所有和根节点具有父子关系的元素
                .filter(x -> parentCheck.apply(root, x))
                // 将这些元素，递归的设置孩子节点
                .peek(x -> setSubChildren.accept(x, makeChildren(x, allData, parentCheck, setSubChildren)))
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {

        List<MenuVo> menuList = getMenuVos();

        List<MenuVo> tree = TreeUtil.makeTree(menuList, x -> x.getPId() == -1L, (x, y) -> x.getId().equals(y.getPId()), MenuVo::setSubMenus);

        // 对每个一级菜单下的所有子菜单按照rank排序  rank大的排在前面
        List<MenuVo> sortTree = TreeUtil.sort(tree, (x, y) -> y.getRank().compareTo(x.getRank()), MenuVo::getSubMenus);

        System.out.println(JSON.toJSONString(sortTree, JSONWriter.Feature.PrettyFormat));

    }

    private static List<MenuVo> getMenuVos() {
        MenuVo menu0 = new MenuVo(0L, -1L);

        MenuVo menu1 = new MenuVo(1L, 0L);
        menu1.setRank(100);

        MenuVo menu2 = new MenuVo(2L, 0L);
        menu2.setRank(1);

        MenuVo menu3 = new MenuVo(3L, 1L);
        MenuVo menu4 = new MenuVo(4L, 1L);

        MenuVo menu5 = new MenuVo(5L, 2L);
        menu5.setRank(5);

        MenuVo menu6 = new MenuVo(6L, 2L);
        MenuVo menu7 = new MenuVo(7L, 3L);
        menu7.setRank(5);
        MenuVo menu8 = new MenuVo(8L, 3L);
        menu8.setRank(1);
        MenuVo menu9 = new MenuVo(9L, 4L);

        return Arrays.asList(menu0, menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9);
    }
}

