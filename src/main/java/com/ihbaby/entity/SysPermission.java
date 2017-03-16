package com.ihbaby.entity;

public class SysPermission {
    /** 资源id */
    private Long id;

    /** 资源名称 */
    private String permissionName;

    /** 资源类型（菜单:1，按钮:2） */
    private Integer permissionType;

    /** 资源key(唯一值) */
    private String permissionKey;

    /** 资源url */
    private String permissionUrl;

    /** 图标 */
    private String permissionIcon;

    /** 权重 */
    private Integer weight;

    /** 是否显示（1:显示 0：不显示） */
    private Boolean sidebar;

    /** 备注 */
    private String remarks;

    /** 医院是否显示 0：不显示 1：显示 */
    private Boolean display;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey == null ? null : permissionKey.trim();
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon == null ? null : permissionIcon.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getSidebar() {
        return sidebar;
    }

    public void setSidebar(Boolean sidebar) {
        this.sidebar = sidebar;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }
}