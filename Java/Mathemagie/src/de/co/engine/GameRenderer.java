package de.co.armadillo.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {

	// Instance of World to render
	private GameWorld world;

	private SpriteBatch batch;
	private ShapeRenderer shape;
	private OrthographicCamera cam;
	
	private float runTime;
	
	public GameRenderer(GameWorld world) {
		this.world = world;
		
		// Create cam
		cam = new OrthographicCamera();
		cam.setToOrtho(true);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		shape = new ShapeRenderer();
		shape.setProjectionMatrix(cam.combined);
	}

	// Responsible for general rendering of the game world
	public void render(float delta) {
		
		// Clear board
		Gdx.gl.glClearColor(255/255.0f, 165/255.0f, 0/255.0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		// Draw background
		batch.draw(AssetLoader.bg, world.getBackground().getX(), world.getBackground().getY());
		batch.draw(AssetLoader.bg, world.getBackground().getX(), world.getBackground().getY()-840);
		
		world.getGameState();
		
		// Draw Health
		for(int i = 0; i < GameState.hitpoints; i++)
			batch.draw(AssetLoader.health, 20+i*50, 770);
		
		// Draw new stage message
		if(world.displayNewStage() && runTime <= 1) {
			AssetLoader.highFont.draw(batch, "New Stage", 300, 400);
			runTime += delta;
		}else{
			world.setDisplayNewStage(false);
			runTime = 0;
		}
		
		// Draw Highscore
		AssetLoader.highFont.draw(batch, "Score: " + GameState.score, 470, 770);
		AssetLoader.highFont.draw(batch, "Stage: " + GameState.stage, 470, 790);
		
		// Draw cross
		batch.draw(AssetLoader.cross, 
				world.getTarget().getEnd().x-35, 
				world.getTarget().getEnd().y-35, 
				35, 
				35, 
				70, 
				70,  	
				2, 
				2, 
				world.getTarget().getSpin());
		
		// Draw equation
		for(int i = 0; i < world.getEnemy().length; i++) {
			if(world.getEnemy()[i].getEquation().getCenter()) {
				AssetLoader.mathFont2.draw(batch, 
						world.getEnemy()[i].getEquation().getQuestion(), 
						world.getEnemy()[i].getCircle().x-20, world.getEnemy()[i].getCircle().y-10);
			}else{
				AssetLoader.mathFont.draw(batch, 
						world.getEnemy()[i].getEquation().getQuestion(), 
						world.getEnemy()[i].getCircle().x-22, world.getEnemy()[i].getCircle().y-5);
			}
		}
		
		batch.end();
		
		// Draw stage
		world.getStage().act(delta);
		world.getStage().draw();
		
		shape.begin(ShapeType.Line);
		
		// Set color
		shape.setColor(255/255.0f, 255/255.0f, 255/255.0f, 1f);
		
		// Draw enemies
		for(int i = 0; i < world.getEnemy().length; i++) {
			shape.circle(world.getEnemy()[i].getCircle().x, 
					world.getEnemy()[i].getCircle().y, 
					world.getEnemy()[i].getCircle().radius);
		}
		// Draw projectile
		shape.circle(world.getProjectile().getCircle().x, 
				world.getProjectile().getCircle().y, 
				world.getProjectile().getCircle().radius);
		
		// Draw character
		shape.rect(world.getChar().getRect().x, 
				world.getChar().getRect().y, 
				world.getChar().getRect().width, 
				world.getChar().getRect().height, 
				25, 
				25, 
				world.getChar().getRotation());
		
		// Draw its cannon
		shape.rect(world.getChar().getCannon().x, 
				world.getChar().getCannon().y, 
				world.getChar().getCannon().width, 
				world.getChar().getCannon().height, 
				5, 
				45, 
				world.getChar().getRotation());
		
		// Mark enemy red
		shape.setColor(255/255.0f, 0/255.0f, 0/255.0f, 1);
		
		shape.circle(world.getTarget().getEnd().x, 
				world.getTarget().getEnd().y, 
				40);
		
		shape.end();
	}
}
