package com.cdutcm.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cdutcm.tcms.biz.model.BaseRecipel;
import com.cdutcm.tcms.biz.model.BaseRecipelItem;
import com.cdutcm.tcms.sys.entity.ZtreeModal;

/**
 * 树形菜单工具类
 * 
 * @author fw
 * 
 */
public class ZtreeUtil {
	/**
	 * 模板对象转树形对象
	 * 
	 * @param baserecipel
	 * @return
	 */
	public static ZtreeModal ToZTree(BaseRecipel baserecipel) {
		ZtreeModal ztreeModalRoot = new ZtreeModal();
		ztreeModalRoot.setName(baserecipel.getName());
		ztreeModalRoot.setId(baserecipel.getId().toString());
		List<ZtreeModal> RootNodes = new ArrayList<ZtreeModal>();
		// 存储basereicpelitem类型集合
		HashSet<String> ctypes = new HashSet<String>();
		if (!StringUtil.isEmptyList(baserecipel.getBaseRecipelItem())) {
			for (BaseRecipelItem baserecipelitem : baserecipel.getBaseRecipelItem()) {
				ctypes.add(baserecipelitem.getCtype());
			}
			for (String ctype : ctypes) {
				ZtreeModal RootNode = new ZtreeModal();
				RootNode.setName(ctype);
				RootNodes.add(RootNode);
			}
			for (ZtreeModal RootNNs : RootNodes) {
				List<ZtreeModal> RootXwNodes = new ArrayList<ZtreeModal>();
				for (BaseRecipelItem baserecipelitem : baserecipel.getBaseRecipelItem()) {
					if (RootNNs.getName().equals(baserecipelitem.getCtype())) {
						ZtreeModal RootXwNode = new ZtreeModal();
						RootXwNode.setId(baserecipelitem.getId().toString());
						// AUTO 设置穴位用法
						RootXwNode.setName(baserecipelitem.getName());
						RootXwNodes.add(RootXwNode);
					}
				}
				RootNNs.setNodes(RootXwNodes);
			}

			ztreeModalRoot.setNodes(RootNodes);
		}
		return ztreeModalRoot;
	}
}
