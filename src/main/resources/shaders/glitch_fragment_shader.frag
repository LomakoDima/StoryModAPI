#version 330 core

in vec2 fragTexCoord;

out vec4 fragColor;

uniform sampler2D texSampler;

void main()
{
    vec2 uv = fragTexCoord;
    vec4 color = texture(texSampler, uv);

    // Глитч-эффект - случайные смещения в цвете
    float offset = 0.02;
    vec4 glitchColor = texture(texSampler, uv + vec2(offset, 0.0));
    color = mix(color, glitchColor, 0.1);

    fragColor = color;
}