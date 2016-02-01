package com.sandarovich.kickstarter.menu;

import com.sandarovich.kickstarter.Categories;
import com.sandarovich.kickstarter.Output;

/**
 * @author Olexander Kolodiazhny 2016
 *
 */

public class CategoryMenu extends AbstractMenu {

    public CategoryMenu(Output output, MenuReader menuReader) {
        super(output,menuReader);
        menuId = 1;
        headerLabel = "Сategories:";
        menuElements = new MenuElement[5];
        menuElements[0] = new MenuElement(Categories.IT.toString(), Actions.SHOW_CATEGORY, 0);
        menuElements[1] = new MenuElement(Categories.SOCIETY.toString(), Actions.SHOW_CATEGORY, 1);
        menuElements[2] = new MenuElement(Categories.SPORT.toString(), Actions.SHOW_CATEGORY, 2);
        menuElements[3] = new MenuElement(Categories.ECOLOGY.toString(), Actions.SHOW_CATEGORY, 3);
        menuElements[4] = new MenuElement("Exit", Actions.EXIT, 4);
    }

    @Override
    public void doAction(int choise) {
		if (isvalidMenuElement(choise)) {
			Actions action = menuElements[choise].getAction();
			if (action == Actions.EXIT) {
				
				AbstractMenu menu = new MainMenu(output, menuReader);
				menu.show();
				menu.doAction(menu.readUserFeedback());
			}

			if (action == Actions.SHOW_CATEGORY) {
				output.print(">> " + menuElements[choise].toString());
				Categories s = Categories.valueOf(menuElements[choise].getNameLabel());
				System.out.println(s);
				AbstractMenu menu = new ProjectMenu(output, menuReader, s);
				menu.show();
				output.print(">> Bye");
			}
		}

    }

}