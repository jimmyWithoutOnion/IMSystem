#ifndef SHA256_H_
#define SHA256_H_

#include<cstdint>
#include<string>
#include<vector>

class Sha256 {
public:
	Sha256() {}

	virtual ~Sha256() {}

	// ��ȡʮ��������ϢժҪ
	std::string GetHexMessageDiges(const std::string& message)const;

protected:
	// Sha256����������߼�����
	inline uint32_t Ch(uint32_t x, uint32_t y, uint32_t z)const;
	inline uint32_t Maj(uint32_t x, uint32_t y, uint32_t z)const;
	inline uint32_t BigSegema0(uint32_t x)const;
	inline uint32_t BigSegema1(uint32_t x)const;
	inline uint32_t SmallSegema0(uint32_t x)const;
	inline uint32_t SmallSegema1(uint32_t x)const;

	// Ԥ������λ��512bit��48Byte)��������
	bool PreProcess(std::vector < uint8_t>& message)const;

	// �ֿ飬512bit��48Byte��Ϊһ��
	bool BlockTxtTo48Byte(const std::vector<uint8_t>& message,
		std::vector<std::vector<uint8_t>>& messageBlocks)const;

	// ������չ��Ϣ��W
	bool CalExtendedWords(const std::vector<uint8_t>& block, std::vector<uint32_t>& words)const;

	// ����ÿ����Ϣ��Ĺ�ϣֵ
	bool CalBlockDigest(const std::vector<uint32_t>& dextendedWord, std::vector<uint32_t>& messageDigest)const;

	// ��32λ�Ĺ�ϣֵת����8λ�Ĺ�ϣֵ
	bool TransferDigestBit32To8(const std::vector<uint32_t>& digest32, std::vector<uint8_t>& degest8)const;

private:
	static std::vector<uint32_t>initConstantKey;
	static std::vector<uint32_t>addConstantKey;
};


inline uint32_t Sha256::Ch(uint32_t x, uint32_t y, uint32_t z)const
{
	return (x & y) ^ (~x & z);
}
inline uint32_t Sha256::Maj(uint32_t x, uint32_t y, uint32_t z)const
{
	return (x & y) ^ (x & z) ^ (y & z);
}
inline uint32_t Sha256::BigSegema0(uint32_t x)const
{
	return (x >> 2 | x << 30) ^ (x >> 13 | x << 19) ^ (x >> 22 | x << 10);
}
inline uint32_t Sha256::BigSegema1(uint32_t x)const
{
	return (x >> 6 | x << 26) ^ (x >> 11 | x << 21) ^ (x >> 25 | x << 7);
}
inline uint32_t Sha256::SmallSegema0(uint32_t x)const
{
	return (x >> 7 | x << 25) ^ (x >> 18 | x << 14) ^ (x >> 3);
}
inline uint32_t Sha256::SmallSegema1(uint32_t x)const
{
	return (x >> 17 | x << 15) ^ (x >> 19 | x << 13) ^ (x >> 10);
}
#endif // SHA256_H_




