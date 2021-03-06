#version 400 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColour;
uniform float shineDamper;
uniform float reflectivity;
uniform float ambianceLight;

void main(void) {

	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);
	vec3 unitCameraVector = normalize(toCameraVector);

	vec3 lightDirection = -unitLightVector;
	vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);

	float brightness = dot(unitNormal, unitLightVector);
	brightness = max(brightness, ambianceLight);

	float specularFactor = dot(unitCameraVector, reflectedLightDirection);
	specularFactor = max(specularFactor, 0.0);

	float dampedFactor = pow(specularFactor, shineDamper);

	vec3 specular = dampedFactor * reflectivity * lightColour;
	vec3 diffuse = brightness * lightColour;

	out_Color = vec4(diffuse, 1.0) * texture(textureSampler, pass_textureCoords)
			+ vec4(specular, 1.0);
}
