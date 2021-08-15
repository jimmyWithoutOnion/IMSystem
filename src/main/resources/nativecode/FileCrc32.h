#ifndef FILECRC32_H_
#define FILECRC32_H_

#include <fstream>
#include <string>
#include <vector>
#include <cstdint>

class FileCrc32
{
public:
	FileCrc32();
	virtual ~FileCrc32() {}

	std::string GetFileCrc(const std::string &filePath);

protected:
	inline uint32_t Crc32_u8(uint32_t crc, uint8_t value);

private:
	std::ifstream inFile;

#ifndef __x86_64__
#ifndef __aarch64__
	static uint32_t crcTable[256];
	static bool tableCreated;
#endif
#endif
};

inline uint32_t FileCrc32::Crc32_u8(uint32_t crc, uint8_t value)
{
#ifdef __x86_64__
	__asm__("crc32b %1,%0"
			: "+r"(crc)
			: "rm"(value));
#else
#ifdef __aarch64__
	__asm__("crc32cb %w[c],%w[c],%w[v]"
			: [c] "+r"(crc)
			: [v] "r"(value));
#else
	crc = FileCrc32::crcTable[(crc ^ value) & 0xFF] ^ (crc >> 8);
#endif
#endif
	return crc;
}

#endif // !FILECRC32_H_
