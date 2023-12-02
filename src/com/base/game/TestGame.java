

package com.base.game;

import com.base.engine.components.*;
import com.base.engine.core.*;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public void Init()
	{
		Mesh mesh = new Mesh("plane3.obj");
		Material material2 = new Material(new Texture("bricks.jpg"), 2f, 7,
			new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.01f, -0.5f);

		Material material = new Material(new Texture("bricks2.jpg"), 0.7f, 6,
				new Texture("bricks2_normal.png"), new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);

		Material materialmonki = new Material(new Texture("monki.jpg"), 1, 8,
				new Texture("monki_normal.jpg"), new Texture("monki_disp.jpg"), 1f, -1.0f);


		Mesh tempMesh = new Mesh("monkey3.obj");

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.AddComponent(meshRenderer);
		planeObject.GetTransform().GetPos().Set(0, -1, 5);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0.05f,0.05f,0.05f), 0.1f);

		directionalLightObject.AddComponent(directionalLight);

		GameObject pointLightObject = new GameObject().AddComponent(new PointLight(new Vector3f(0, 0.6f, 0.6f), 0.9f, new Attenuation(0, 0, 0.5f)));
		pointLightObject.GetTransform().GetPos().Set(0,0,-2);

	//	SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.7f,
	//			new Attenuation(0,0,0.1f), 0.7f);

		GameObject[] spotLights = new GameObject[14];

		Vector3f[] colors = {
				new Vector3f(0.835f,0.176f,0),
				new Vector3f(0.937f,0.463f,0.153f),
				new Vector3f(1f,0.604f,0.337f),
				new Vector3f(1f,1f,1f),
				new Vector3f(0.819f,0.384f,0.643f),
				new Vector3f(0.709f,0.337f,0.565f),
				new Vector3f(0.639f,0.008f,0.384f)
		};

		int zVal = -1;

		for(int j = 0; j < 7; j++) {
			for (int i = 0; i < 2; i++) {


				spotLights[i] = new GameObject();
				spotLights[i].AddComponent(new SpotLight(colors[j], 0.2f,
						new Attenuation(0, 0, 0.3f), 0f));

				if (i == 0) {
					spotLights[i].GetTransform().GetPos().Set(8.5f, 0.2f, zVal);
					spotLights[i].GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-90.0f)));
				} else {
					spotLights[i].GetTransform().GetPos().Set(-8.5f, 0.2f, zVal);
					spotLights[i].GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90.0f)));
				}

				AddObject(spotLights[i]);

			}

			zVal += 2.95f;

		}

		/*GameObject spotLightObject = new GameObject();
		spotLightObject.AddComponent(spotLight);

		spotLightObject.GetTransform().GetPos().Set(9, 0.2f, 5);
		spotLightObject.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-90.0f)));
		*/

		AddObject(planeObject);
		AddObject(directionalLightObject);
		AddObject(pointLightObject);
		//AddObject(spotLightObject);


		GameObject cameraObject = new GameObject().AddComponent(new FreeLook(0.2f)).AddComponent(new FreeMove(10.0f))
			.AddComponent(new Camera(new Matrix4f().InitPerspective((float) Math.toRadians(70.0f),
					(float) Window.GetWidth() / (float) Window.GetHeight(), 0.01f, 1000.0f))).
				AddComponent(new SpotLight(new Vector3f(0.9f,0.6f,0.45f), 2f,
						new Attenuation(0,0,0.25f), 0.1f));

		AddObject(cameraObject);

		cameraObject.GetTransform().GetPos().Set(new Vector3f(0,1,16));
		cameraObject.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(180)));

		GameObject goldMonkiLeft = new GameObject().AddComponent(new LookAtComponent()).AddComponent(new MeshRenderer(tempMesh, materialmonki));
		AddObject(goldMonkiLeft);

		goldMonkiLeft.GetTransform().GetPos().Set(9, 6, 2);
		goldMonkiLeft.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-70.0f)));

		GameObject goldMonkiRight = new GameObject().AddComponent(new LookAtComponent()).AddComponent(new MeshRenderer(tempMesh, materialmonki));
		AddObject(goldMonkiRight);

		goldMonkiRight.GetTransform().GetPos().Set(-9, 6, 2);
		goldMonkiRight.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-70.0f)));

		GameObject pointLightLeft = new GameObject().AddComponent(new PointLight(new Vector3f(1, 0.05f, 0), 1.2f, new Attenuation(0, 0, 0.5f)));

		pointLightLeft.GetTransform().GetPos().Set(9,8.5f,2);
		pointLightLeft.GetTransform().SetRot(new Quaternion(new Vector3f(1,0,0), (float) Math.toRadians(90)));
		AddObject(pointLightLeft);

		GameObject pointLightRight = new GameObject().AddComponent(new PointLight(new Vector3f(1, 0.05f, 0f), 1.2f, new Attenuation(0, 0, 0.5f)));

		pointLightRight.GetTransform().GetPos().Set(-9,8.5f,2);
		pointLightRight.GetTransform().SetRot(new Quaternion(new Vector3f(1,0,0), (float) Math.toRadians(90)));
		AddObject(pointLightRight);


		GameObject stoneMonki = new GameObject().AddComponent(new MeshRenderer(new Mesh("monkey3.obj"), material2));
		stoneMonki.GetTransform().GetPos().Set(0,0,-2) ;
		AddObject(stoneMonki);



		directionalLight.GetTransform().SetRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(-45)));

		//GameObject spotLightObject = new GameObject().AddComponent(new FreeLook(0.5f)).AddComponent(new FreeMove(10.0f))



		//spotLightObject.GetTransform().GetPos().Set(cameraObject.GetTransform().GetPos());
		//spotLightObject.GetTransform().SetRot(cameraObject.GetTransform().GetRot());

		//AddObject(spotLightObject);
	}
}
