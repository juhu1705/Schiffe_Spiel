#version 330

layout (location = 0) in vec3 position;

uniform mat4 projection_matrix;
uniform mat4 model_matrix;
uniform mat4 view_matrix;

void main()	{
	gl_Position = vec4(position,1) * model_matrix * view_matrix * projection_matrix;
}