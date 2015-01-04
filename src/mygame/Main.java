package mygame;

import AI.AIControl;
import AI.AIControl2;
import AI.SoundEmitterControl;
import physics.PhysicsTestHelper;
import animations.AdvAnimationManagerControl;
import appstate.InputAppState;
import animations.AIAnimationControl;
import animations.CharacterInputAnimationAppState;
import characters.MyGameCharacterControl;
import characters.AICharacterControl;
import characters.ChaseCamCharacter;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import java.util.List;

/**
 * test
 * @author Prof Wireman
 */
public class Main extends SimpleApplication {

    private BulletAppState bulletAppState;
    private AdvAnimationManagerControl animControl;
    
    private Vector3f normalGravity = new Vector3f(0, -9.81f, 0);
    private CameraNode camNode;
    public static Material lineMat;
    private Vector3f camLocation = new Vector3f(0,-20f,0);
    private Vector3f lookAtDirection = new Vector3f(0,-0.8f,-0.2f);
    private float camDistance = 30f;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        cam.lookAtDirection(camLocation, Vector3f.UNIT_Y);
        camLocation.set(cam.getDirection().mult(-camDistance));
        cam.setLocation(camLocation);
        
        lineMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        // activate physics
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);

        // init a physics test scene
        PhysicsTestHelper.createWeek5PhysicsWorld(rootNode, assetManager, bulletAppState.getPhysicsSpace());
        //PhysicsTestHelper.createBallShooter(this, rootNode, bulletAppState.getPhysicsSpace());

        //createPlayerCharacter();
        createAICharacter();
        

        
//        characterNode.attachChild(model);

        // Add character node to the rootNode
//        rootNode.attachChild(characterNode);
        
        
        
        

    }
    
    private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }
    
    private void createAICharacter() {
        // Load model, attach to character node
       Node sinbad = (Node) assetManager.loadModel("Models/Jaime/Jaime.j3o");
        
        sinbad.setLocalScale(1.50f);
        
        //Node mainPlayer = createPlayerCharacter();
        AICharacterControl physicsCharacter = new AICharacterControl(0.3f, 2.5f, 8f);
        
        sinbad.addControl(physicsCharacter);
        getPhysicsSpace().add(physicsCharacter);
        rootNode.attachChild(sinbad);
        sinbad.addControl(new AIControl());
        sinbad.addControl(new AIAnimationControl());
        
        CameraNode camNode = new CameraNode("CamNode", cam);
        camNode.setControlDir(CameraControl.ControlDirection.CameraToSpatial);
        
        Geometry g = new Geometry("", new Box(1,1,1));
        g.setModelBound(new BoundingSphere(5f, Vector3f.ZERO));
        g.updateModelBound();
        g.setMaterial(lineMat);
        camNode.attachChild(g);
        camNode.addControl(new SoundEmitterControl());
        getFlyByCamera().setMoveSpeed(25);
        rootNode.attachChild(camNode);
        List<Spatial> targets = new ArrayList<Spatial>();
        targets.add(camNode);
        //targets.add(mainPlayer);
        
        //jaime.getControl(AIControl.class).setState(AIControl.State.Follow);
        sinbad.getControl(AIControl.class).setTargetList(targets);
        //jaime.getControl(AIControl.class).setTarget(camNode);
    }
    
    private Node createPlayerCharacter() {
        
        stateManager.attach(bulletAppState);
        //stateManager.detach(stateManager.getState(FlyCamAppState.class));
        
        Node playerNode = (Node) assetManager.loadModel("Models/Jaime/Jaime.j3o");
        playerNode.setLocalTranslation(12f, 0, 8f);
        ChaseCamCharacter charControl = new ChaseCamCharacter(-4.5f, 2.5f, 0f);
        //MyGameCharacterControl charControl = new MyGameCharacterControl(0,-20f,0);
        
        charControl.setGravity(normalGravity);
        charControl.setCamera(cam);
        
//        ChaseCamera chaseCam = new ChaseCamera(cam, playerNode, inputManager);
//        chaseCam.setDragToRotate(false);
//        chaseCam.setSmoothMotion(true);
//        chaseCam.setLookAtOffset(new Vector3f(0, 1f, 0));
//        chaseCam.setDefaultDistance(7f);
//        chaseCam.setMaxDistance(8f);
//        chaseCam.setMinDistance(6f);
//       
//        chaseCam.setTrailingSensitivity(50);
//        chaseCam.setChasingSensitivity(10);
//        chaseCam.setRotationSpeed(10);
//        chaseCam.setDragToRotate(true);
//        chaseCam.setToggleRotationTrigger();

        playerNode.addControl(charControl);
        bulletAppState.getPhysicsSpace().add(charControl);

        CharacterInputAnimationAppState appState = new CharacterInputAnimationAppState();
        appState.addActionListener(charControl);
        appState.addAnalogListener(charControl);
        //appState.setChaseCamera(chaseCam);
        stateManager.attach(appState);
        rootNode.attachChild(playerNode);
        inputManager.setCursorVisible(false);
        
        animControl = new AdvAnimationManagerControl("animations/resources/animations-jaime.properties");
        playerNode.addControl(animControl);
        
        appState.addActionListener(animControl);
        appState.addAnalogListener(animControl);
        
        return playerNode;
    }

//    @Override
//    public void simpleUpdate(float tpf) {
//        //TODO: add update code
//    }
//
//    @Override
//    public void simpleRender(RenderManager rm) {
//        //TODO: add render code
//    }
}
