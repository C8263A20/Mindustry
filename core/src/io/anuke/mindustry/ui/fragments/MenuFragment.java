package io.anuke.mindustry.ui.fragments;

import com.badlogic.gdx.Gdx;
import io.anuke.mindustry.Mindustry;
import io.anuke.mindustry.Vars;
import io.anuke.mindustry.core.GameState;
import io.anuke.mindustry.core.GameState.State;
import io.anuke.mindustry.ui.MenuButton;
import io.anuke.mindustry.ui.PressGroup;
import io.anuke.ucore.scene.builders.imagebutton;
import io.anuke.ucore.scene.builders.table;

import static io.anuke.mindustry.Vars.*;

public class MenuFragment implements Fragment{
	
	public void build(){
		if(!android){
			//menu table
			new table(){{
				
				new table(){{
					PressGroup group = new PressGroup();
					
					float scale = 4f;
					defaults().size(100*scale, 21*scale).pad(-10f);

					add(new MenuButton("$text.play", group, ui.levels::show));
					row();

					add(new MenuButton("$text.joingame", group, ui.join::show));
					row();

					
					add(new MenuButton("$text.tutorial", group, ()-> control.playMap(world.maps().getMap("tutorial"))));
					row();

					add(new MenuButton("$text.loadgame", group, ui.load::show));
					row();

					if(!gwt){
						add(new MenuButton("$text.editor", group, ui.editor::show));
						row();
					}
					
					add(new MenuButton("$text.settings", group, ui.settings::show));
					row();
					
					if(!gwt){
						add(new MenuButton("$text.quit", group, Gdx.app::exit));
					}
					get().margin(16);
				}}.end();
	
				visible(()-> GameState.is(State.menu));
			}}.end();
		}else{
			new table(){{
				new table(){{
					defaults().size(120f).pad(5);
					float isize = 14f*4;
					
					new imagebutton("icon-play-2", isize, ui.levels::show).text("$text.play").padTop(4f);
					
					new imagebutton("icon-tutorial", isize, () -> control.playMap(world.maps().getMap("tutorial"))).text("$text.tutorial").padTop(4f);
					
					new imagebutton("icon-load", isize, ui.load::show).text("$text.load").padTop(4f);

					new imagebutton("icon-add", isize, ui.join::show).text("$text.joingame").padTop(4f);
					
					row();

					new imagebutton("icon-editor", isize, ui.editor::show).text("$text.editor").padTop(4f);
	
					new imagebutton("icon-tools", isize, ui.settings::show).text("$text.settings").padTop(4f);

					new imagebutton("icon-info", isize, ui.about::show).text("$text.about.button").padTop(4f);

					new imagebutton("icon-donate", isize, Mindustry.platforms::openDonations).text("$text.donate").padTop(4f);

					visible(()->GameState.is(State.menu));
				}}.end();
			}}.end();
		}
		
		//extra icons in top right
		new table(){{
			atop().aright();
			if(Mindustry.hasDiscord){
				new imagebutton("icon-discord", 30f, ui.discord::show).margin(14);
			}
			if(!Vars.android) {
				new imagebutton("icon-info", 30f, ui.about::show).margin(14);
			}
		}}.end().visible(()->GameState.is(State.menu));
	}
}
