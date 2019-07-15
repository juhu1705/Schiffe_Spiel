#version 330

in vec3 outPositions;
in vec2 textureCoordinates_FS;
in float distance1;

out vec4 fragColor;

uniform sampler2D normalmap;

const vec3 direction = vec3(0.1,-1,0.1);
const float intensity = 1.2;

float diffuse(vec3 direction, vec3 normal, float intensity)
{
	return max(0.01, dot(normal, -direction) * intensity);
}

void main()
{
	vec3 normal = texture(normalmap, outPositions.xz).rgb;

	float diffuse = diffuse(direction, normal, intensity);

	fragColor = vec4(0.1,1.0,0.1,1.0) * diffuse;
}