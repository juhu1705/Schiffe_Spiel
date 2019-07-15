#version 330

layout (location = 0) in vec3 position;
in vec2 textureCoordinates;

out vec3 outPositions;
out vec2 textureCoordinates_FS;
out float distance1;

uniform mat4 modelViewProjectionMatrix;
uniform mat4 localMatrix;
uniform mat4 worldMatrix;
uniform vec3 cameraPosition;
uniform float[((10 + 1) * (10 + 1)) * 3] positionArray;

void main()	{
	
	vec3 positionUsed = position;
	for(int i = 0; i < positionArray.length; i += 3)	{
		int index = i;
		float x = positionArray[index];
		float z = positionArray[(index + 2)];
		if(x == positionUsed.x && z == positionUsed.z)
			positionUsed = vec3(positionUsed.x, positionArray[index + 1], positionUsed.z);
	}
	vec4 localPosition = (localMatrix * vec4(position,1));
	vec4 test = (localMatrix * vec4(positionUsed, 1));
	gl_Position = worldMatrix * vec4(localPosition.x, test.y, localPosition.zw);
	outPositions = (localMatrix * vec4(positionUsed,1)).xyz;
	distance1 = distance(cameraPosition, (localMatrix * vec4(positionUsed,1)).xyz);
	textureCoordinates_FS = position.xz;
}