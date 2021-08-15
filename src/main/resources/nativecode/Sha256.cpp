#include "Sha256.h"

#include <cstdint>
#include <string>
#include <vector>
#include <sstream>
#include <iomanip>
#include <cassert>

std::vector<uint32_t> Sha256::initConstantKey = {
	0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
	0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19};

std::vector<uint32_t> Sha256::addConstantKey = {
	0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5,
	0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
	0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3,
	0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
	0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc,
	0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
	0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7,
	0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
	0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
	0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
	0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3,
	0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
	0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5,
	0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
	0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
	0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2};

std::string Sha256::GetHexMessageDiges(const std::string &message) const
{
	if (message.empty())
	{
		return "";
	}

	std::vector<uint8_t> messageUint8;
	for (const auto &it : message)
	{
		messageUint8.push_back(static_cast<uint8_t>(it));
	}
	assert(PreProcess(messageUint8));

	std::vector<std::vector<uint8_t>> messageBlocks;
	assert(BlockTxtTo48Byte(messageUint8, messageBlocks));

	std::vector<uint32_t> messageDigestBit32(initConstantKey);
	std::vector<uint32_t> extendedWord;

	for (const auto &block : messageBlocks)
	{
		assert(CalExtendedWords(block, extendedWord));
		assert(CalBlockDigest(extendedWord, messageDigestBit32));
	}

	std::vector<uint8_t> messageDigestBit8(32);
	assert(TransferDigestBit32To8(messageDigestBit32, messageDigestBit8));

	std::stringstream messageDigestStr;
	messageDigestStr << std::hex << std::setiosflags(std::ios::uppercase);
	for (const auto &it : messageDigestBit8)
	{
		messageDigestStr << static_cast<int>(it);
	}

	return messageDigestStr.str();
}

bool Sha256::PreProcess(std::vector<uint8_t> &message) const
{
	if (message.empty())
	{
		return false;
	}

	// ԭʼ��Ϣ�ֽڳ��ȣ�bit���ȣ�����0x80һ���ֽں�ĳ���
	size_t originalByteLen = message.size();
	message.push_back(0x80);
	size_t addOneByteLen = originalByteLen + 1;

	// ��Ҫ���ӵ�0x00�ֽڳ���
	size_t zeroByteLen = (addOneByteLen % 64) / 57 * 64 + 56 - addOneByteLen % 64;
	for (size_t i = 0; i < zeroByteLen; i++)
	{
		message.push_back(0x00);
	}

	uint64_t originalBitLen = static_cast<uint64_t>(originalByteLen) * 8;
	for (int i = 1; i <= 8; i++)
	{
		message.push_back(static_cast<uint8_t>(originalBitLen >> (64 - 8 * i)));
	}

	return true;
}

bool Sha256::BlockTxtTo48Byte(const std::vector<uint8_t> &message,
							  std::vector<std::vector<uint8_t>> &messageBlocks) const
{
	if (message.size() % 64 != 0)
	{
		return false;
	}

	size_t loopNum = message.size() / 64;
	for (size_t i = 0; i < loopNum; i++)
	{
		std::vector<uint8_t> temp(message.begin() + i * 64, message.begin() + (i + 1) * 64);
		messageBlocks.emplace_back(temp);
	}

	return true;
}

bool Sha256::CalExtendedWords(const std::vector<uint8_t> &block, std::vector<uint32_t> &words) const
{
	if (block.size() != 64)
	{
		return false;
	}

	words.resize(64);

	for (int i = 0, j = 0; i < 16; i++, j += 4)
	{
		words[i] = static_cast<uint32_t>(block[j] << 24) |
				   static_cast<uint32_t>(block[j + 1] << 16) |
				   static_cast<uint32_t>(block[j + 2] << 8) |
				   static_cast<uint32_t>(block[j + 3]);
	}

	for (int i = 16; i < 64; i++)
	{
		words[i] = SmallSegema1(words[i - 2]) + words[i - 7] + SmallSegema0(words[i - 15]) + words[i - 16];
	}

	return true;
}

bool Sha256::CalBlockDigest(const std::vector<uint32_t> &extendedWord,
							std::vector<uint32_t> &messageDigest) const
{
	if (extendedWord.size() != 64 || messageDigest.size() != 8)
	{
		return false;
	}

	auto tempDigest = messageDigest;
	for (int i = 0; i < 64; i++)
	{
		uint32_t temp1 = tempDigest[7] + BigSegema1(tempDigest[4]) +
						 Ch(tempDigest[4], tempDigest[5], tempDigest[6]) +
						 addConstantKey[i] + extendedWord[i];
		uint32_t temp2 = BigSegema0(tempDigest[0]) + Maj(tempDigest[0], tempDigest[1], tempDigest[2]);
		tempDigest[7] = tempDigest[6];
		tempDigest[6] = tempDigest[5];
		tempDigest[5] = tempDigest[4];
		tempDigest[4] = tempDigest[3] + temp1;
		tempDigest[3] = tempDigest[2];
		tempDigest[2] = tempDigest[1];
		tempDigest[1] = tempDigest[0];
		tempDigest[0] = temp1 + temp2;
	}

	for (int i = 0; i < 8; i++)
	{
		messageDigest[i] += tempDigest[i];
	}

	return true;
}

bool Sha256::TransferDigestBit32To8(const std::vector<uint32_t> &digest32, std::vector<uint8_t> &digest8) const
{
	if (digest32.size() != 8 || digest8.size() != 32)
	{
		return false;
	}

	for (int i = 0, j = 0; i < 8; i++)
	{
		digest8[j++] = static_cast<uint8_t>(digest32[i] >> 24);
		digest8[j++] = static_cast<uint8_t>(digest32[i] >> 16);
		digest8[j++] = static_cast<uint8_t>(digest32[i] >> 8);
		digest8[j++] = static_cast<uint8_t>(digest32[i]);
	}

	return true;
}
