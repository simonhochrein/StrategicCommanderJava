package com.simonhochrein.StrategicCommander.client.renderer;

import org.joml.Vector3f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIVector3D;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.assimp.Assimp.*;
import static org.lwjgl.opengl.GL11.*;

public class Mesh {
    List<Vector3f> normals;
    List<Vector3f> vertices;
    List<Integer> indices;
    List<Vector3f> uv;

    public Mesh(String path) {
        AIScene aiScene = aiImportFile(path, aiProcess_JoinIdenticalVertices | aiProcess_GenNormals | aiProcess_Triangulate | aiProcess_FixInfacingNormals);

        int numMeshes = aiScene.mNumMeshes();
        PointerBuffer aiMeshes = aiScene.mMeshes();
        vertices = new ArrayList<>();
        indices = new ArrayList<>();
        normals = new ArrayList<>();
        uv = new ArrayList<>();
        for (int i = 0; i < numMeshes; i++) {
            AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
            AIVector3D.Buffer _vertices = aiMesh.mVertices();
            AIVector3D.Buffer _normals = aiMesh.mNormals();
            AIFace.Buffer _faces = aiMesh.mFaces();

            for (int i2 = 0; i2 < aiMesh.mNumVertices(); i2++) {
                AIVector3D vertex = _vertices.get(i2);
                AIVector3D normal = _normals.get(i2);

                AIVector3D uv_point = aiMesh.mTextureCoords(0).get(i2);

                uv.add(new Vector3f(uv_point.x(), uv_point.y(), uv_point.z()));
                vertices.add(new Vector3f(vertex.x(), vertex.y(), vertex.z()));
                normals.add(new Vector3f(normal.x(), normal.y(), normal.z()));
            }
            for (int i2 = 0; i2 < aiMesh.mNumFaces(); i2++) {
                AIFace face = _faces.get(i2);
                for (int i3 = 0; i3 < face.mNumIndices(); i3++) {
                    indices.add(face.mIndices().get(i3));
                }
            }
        }
    }

    public void Render() {
        glBegin(GL_TRIANGLES);

        for (int index :
                indices) {
            Vector3f vertex = vertices.get(index);
            Vector3f uv_point = uv.get(index);
            Vector3f normal = normals.get(index);

            glNormal3f(normal.x, normal.y, normal.z);
            glTexCoord2f(uv_point.x, uv_point.y);
            glVertex3f(vertex.x, vertex.y, vertex.z);
        }

        glEnd();
    }
}
