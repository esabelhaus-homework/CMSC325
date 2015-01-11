package mygame;

import AI.AIControl_SM;
import characters.NavMeshNavigationControl;
import animations.AdvAnimationManagerControl;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import characters.AICharacterControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import jme3tools.optimize.GeometryBatchFactory;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private BulletAppState bulletAppState;
    
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

   @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        getFlyByCamera().setMoveSpeed(45f);
        cam.setLocation(new Vector3f(20, 20, 20));
        cam.lookAt(new Vector3f(0,0,0), Vector3f.UNIT_Y);
        
        Node scene = setupWorld();
        
//        DirectionalLight l = new DirectionalLight();
//        rootNode.addLight(l);
        setupCharacter(scene);
    }
    
    private Node setupWorld(){
        Node scene = (Node) assetManager.loadModel("Scenes/newScene.j3o");
        
        rootNode.attachChild(scene);

        //FilterPostProcessor processor = (FilterPostProcessor) assetManager.loadAsset("Effects/Water.j3f");
        //viewPort.addProcessor(processor);
        

        //TODO: navmesh only for debug
        Geometry navGeom = new Geometry("NavMesh");
        navGeom.setMesh(((Geometry) scene.getChild("NavMesh" )).getMesh());
        Material green = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        green.setColor("Color", ColorRGBA.Green);
        green.getAdditionalRenderState().setWireframe(true);
        navGeom.setMaterial(green);

        rootNode.attachChild(navGeom); 
        
        
        Spatial terrain = scene.getChild("terrain-newScene");
        terrain.addControl(new RigidBodyControl(0));
        bulletAppState.getPhysicsSpace().addAll(terrain);
        return scene;
    }

     private void setupCharacter(Node scene){
        // Load model, attach to character node
        Node aiCharacter = (Node) assetManager.loadModel("Models/Jaime/Jaime.j3o");

        AICharacterControl physicsCharacter = new AICharacterControl(0.3f, 2.5f, 8f);
        aiCharacter.addControl(physicsCharacter);
        bulletAppState.getPhysicsSpace().add(physicsCharacter);
        aiCharacter.setLocalTranslation(0, 10, 0);
        aiCharacter.setLocalScale(2f);
        scene.attachChild(aiCharacter);
        NavMeshNavigationControl navMesh = new NavMeshNavigationControl((Node) scene);
        
        aiCharacter.addControl(navMesh);
        navMesh.moveTo(new Vector3f(60, 10, -55));
        //aiCharacter.addControl(new NavMeshNavigationControl((Node) scene));
        
        //aiCharacter.getControl(NavMeshNavigationControl.class).moveTo(new Vector3f(80, 20, 20));
        
    }
}
