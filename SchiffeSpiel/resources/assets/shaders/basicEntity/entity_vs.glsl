#version 330

layout (location = 0) in vec3 position;


uniform mat4 model_matrix;
uniform mat4 view_matrix;
uniform mat4 projection_matrix;


void main()	{
	gl_Position = model_matrix * view_matrix * projection_matrix * vec4(position,1);
}