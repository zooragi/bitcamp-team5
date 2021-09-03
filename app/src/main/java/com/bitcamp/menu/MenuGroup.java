package com.bitcamp.menu;

import java.util.ArrayList;

import java.util.List;
import java.util.Stack;

import com.bitcamp.goodplace.Handler.AuthLoginHandler;
import com.bitcamp.util.Prompt;

// 역할
// - 다른 메뉴를 포함하는 컨테이너 역할을 수행한다.
// 
public class MenuGroup extends Menu {

	static Stack<Menu> breadCrumb = new Stack<>();
	
	Menu[] childs = new Menu[100];
	int size;
	boolean disablePrevMenu;
	String prevMenuTitle = "이전 메뉴";

	public MenuGroup(String title) {
		super(title);
	}

	public MenuGroup(String title, boolean disablePrevMenu) {
		super(title);
		this.disablePrevMenu = disablePrevMenu;
	}

	public void setPrevMenuTitle(String prevMenuTitle) {
		this.prevMenuTitle = prevMenuTitle;
	}

	// MenuGroup이 포함하는 하위 Menu를 다룰 수 있도록 메서드를 정의한다.
	public void add(Menu child) {
		if (this.size == this.childs.length) {
			return; // 하위 메뉴를 저장하는 배열이 꽉 찼다면 더이상 저장해서는 안된다.
		}
		this.childs[this.size++] = child;
	}

	// 배열에 들어 있는 Menu 객체를 찾아 제거한다.
	public Menu remove(Menu child) {
		int index = indexOf(child);
		if (index == -1) {
			return null;
		}
		for (int i = index + 1; i < this.size; i++) {
			this.childs[i - 1] = this.childs[i];
		}
		childs[--this.size] = null;
		return child;
	}

	// 배열에 들어 있는 Menu 객체의 인덱스를 알아낸다.
	public int indexOf(Menu child) {
		for (int i = 0; i < this.size; i++) {
			if (this.childs[i] == child) {
				return i;
			}
		}
		return -1;
	}

	// 배열에 들어 있는 Menu 객체를 찾는다.
	public Menu getMenu(String title) {
		for (int i = 0; i < this.size; i++) {
			if (this.childs[i].title.equals(title)) {
				return this.childs[i];
			}
		}
		return null;
	}

	public Menu getMenuByIndex(int index) {
		if (index < 0) {
			return null;
		}
		return childs[index];
	}

	@Override
	public void execute() {
		breadCrumb.push(this);
		while (true) {
			System.out.printf("\n[%s]\n", getTitleMenus());
			List<Menu> menuList = new ArrayList<>();
			for(int i = 0; i < this.size ; i++) {
				if(childs[i].enableState == Menu.ENABLE_LOGOUT &&
						AuthLoginHandler.getLoginUser() == null) {
					menuList.add(childs[i]);
				} else if(childs[i].enableState == Menu.ENABLE_LOGIN &&
						AuthLoginHandler.getLoginUser() != null) {
					menuList.add(childs[i]);
				} else if(childs[i].enableState == Menu.ENABLE_ALL) {
					menuList.add(childs[i]);
				}
			}
			
			for (int i = 0; i < menuList.size(); i++) {
				System.out.printf("%d. %s\n", i + 1, menuList.get(i).title);
			}

			if (!disablePrevMenu) {
				System.out.printf("0. %s\n", this.prevMenuTitle);
			}
			try {
				int menuNo = Prompt.inputInt("선택> ");
				if (menuNo == 0 && !disablePrevMenu) {
					breadCrumb.pop();
					return;
				}
				if (menuNo < 0 || menuNo > this.size) {
					System.out.println("무효한 메뉴 번호입니다.");
					continue;
				}
				menuList.get(menuNo - 1).execute();
			} catch (Exception e) {
				System.out.println("------------------------------------------");
				System.out.printf("오류 발생: %s\n", e.getClass().getName());
				System.out.println("------------------------------------------");
			}
		}
	}
	
	public String getTitleMenus() {
		String path = "";
		for(Menu menu : breadCrumb ) {
			if(path.length() > 0) {
				path += " / "; 
			}
			path += menu.title;
		}
		return path;
	}
}
