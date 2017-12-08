package com.armadillo.testimony.engine;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class MapBodyBuilder
{
	
    static Array<Body> bodies = new Array<Body>();
	
    public static Array<Body> buildShapes(Map map, float pixels, World world)
    {
        MapObjects objects = map.getLayers().get("Obstacles").getObjects();

        for(MapObject object : objects)
        {
            if (object instanceof TextureMapObject) { continue;}
            
            if (object instanceof RectangleMapObject) {
                getRectangle((RectangleMapObject)object, world);
            }
            else if (object instanceof PolygonMapObject) {
                getPolygon((PolygonMapObject)object, world);
            }
        }        
        return bodies;
    }
    
    private static void getRectangle(RectangleMapObject rectangleObject, World world) {
    	Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();

        polygon.setAsBox(rectangle.width / Constants.PIXELS_TO_METERS, rectangle.height / Constants.PIXELS_TO_METERS);
        
        BodyDef bd = new BodyDef();
        bd.type = BodyType.StaticBody;
        bd.position.set(rectangle.x / Constants.PIXELS_TO_METERS, rectangle.y / Constants.PIXELS_TO_METERS);
        Body body = world.createBody(bd);
        body.createFixture(polygon, 1);

        bodies.add(body);

        polygon.dispose();
    }
    
    private static void getPolygon(PolygonMapObject polygonObject, World world) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            worldVertices[i] = vertices[i] / Constants.PIXELS_TO_METERS;
        }

        polygon.set(worldVertices);
        BodyDef bd = new BodyDef();
        bd.type = BodyType.StaticBody;
        Body body = world.createBody(bd);
        body.createFixture(polygon, 1);

        bodies.add(body);

        polygon.dispose();
    }
}