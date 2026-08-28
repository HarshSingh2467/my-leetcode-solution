class Solution(object):
    def lexPalindromicPermutation(self, s, target):
        """
        :type s: str
        :type target: str
        :rtype: str
        """
        cnt = [0] * 26
        for ch in s:
            cnt[ord(ch) - 97] += 1

        odd = 0
        mid = -1
        for i in range(26):
            if cnt[i] % 2 == 1:
                odd += 1
                mid = i
        if odd > 1:
            return ""

        for i in range(26):
            cnt[i] //= 2

        n = len(s)
        half = n // 2
        left = [''] * half

        def build(pos, greater):
            if pos == half:
                left_part = ''.join(left)
                right_part = left_part[::-1]
                pal = left_part + (chr(97 + mid) if n % 2 else '') + right_part
                return pal > target
            start = 97 if greater else ord(target[pos])
            for c in range(start, 123):
                idx = c - 97
                if cnt[idx] == 0:
                    continue
                left[pos] = chr(c)
                cnt[idx] -= 1
                now_greater = greater or c > ord(target[pos])
                if build(pos + 1, now_greater):
                    return True
                cnt[idx] += 1
            return False

        if build(0, False):
            left_part = ''.join(left)
            right_part = left_part[::-1]
            return left_part + (chr(97 + mid) if n % 2 else '') + right_part
        return ""