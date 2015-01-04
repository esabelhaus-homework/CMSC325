package mygame;

import physics.PhysicsTestHelper;
import characters.ChaseCamCharacter;
import animations.AdvAnimationManagerControl;
import animations.CharacterInputAnimationAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.ChaseCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

       private BulletAppState bulletAppState;
    private Vector3f normalGravity = new Vector3f(0, -9.81f, 0);
    
    private Node coverStructures = new Node("Cover");
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //Add the Scene
        Spatial scene = assetManager.loadModel("Scenes/Week4Scene.j3o");
        rootNode.attachChild(scene);
        
        bulletAppState = new BulletAppState();
        
        stateManager.attach(bulletAppState);
        stateManager.detach(stateManager.getState(FlyCamAppState.class));

        PhysicsTestHelper.createWeek4PhysicsWorld(rootNode, assetManager, bulletAppState.getPhysicsSpace());

        Node playerNode = (Node) assetManager.loadModel("Models/Jaime/Jaime.j3o");
        
        ChaseCamCharacter charControl = new ChaseCamCharacter(0.5f, 2.5f, 8f);
        charControl.setGravity(normalGravity);
        charControl.setCamera(cam);
        
        ChaseCamera chaseCam = new ChaseCamera(cam, playerNode, inputManager);
        chaseCam.setDragToRotate(false);
        chaseCam.setSmoothMotion(true);
        chaseCam.setLookAtOffset(new Vector3f(0, 1f, 0));
        chaseCam.setDefaultDistance(7f);
        chaseCam.setMaxDistance(8f);
        chaseCam.setMinDistance(6f);
       
        chaseCam.setTrailingSensitivity(50);
        chaseCam.setChasingSensitivity(10);
        chaseCam.setRotationSpeed(10);
//        chaseCam.setDragToRotate(true);
//        chaseCam.setToggleRotationTrigger();

        playerNode.addControl(charControl);
        bulletAppState.getPhysicsSpace().add(charControl);

        CharacterInputAnimationAppState appState = new CharacterInputAnimationAppState();
        appState.addActionListener(charControl);
        appState.addAnalogListener(charControl);
        appState.setChaseCamera(chaseCam);
        stateManager.attach(appState);
        rootNode.attachChild(playerNode);
        inputManager.setCursorVisible(false);
        
        AdvAnimationManagerControl animControl = new AdvAnimationManagerControl("animations/resources/animations-jaime.properties");
        playerNode.addControl(animControl);
        
        appState.addActionListener(animControl);
        appState.addAnalogListener(animControl);
    }
}
