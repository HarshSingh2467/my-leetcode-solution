class Solution(object):
    def lexGreaterPermutation(self, s, target):
        """
        :type s: str
        :type target: str
        :rtype: str
        """
        n = len(s)
        count = [0] * 26
        for ch in s:
            count[ord(ch) - ord('a')] += 1
        result = []

        def can_make_greater(start):
            # Build the LARGEST string from remaining characters
            largest = []
            for c in range(25, -1, -1):
                while count[c] > 0:
                    largest.append(chr(ord('a') + c))
                    count[c] -= 1
            # Restore count (this was only a check)
            for ch in largest:
                count[ord(ch) - ord('a')] += 1
            return ''.join(largest) > target[start:]

        for i in range(n):
            tc = ord(target[i]) - ord('a')
            if count[tc] > 0:
                count[tc] -= 1
                if can_make_greater(i + 1):
                    result.append(target[i])
                    continue
                count[tc] += 1
            # Choose the smallest character greater than target[i]
            for c in range(tc + 1, 26):
                if count[c] > 0:
                    result.append(chr(ord('a') + c))
                    count[c] -= 1
                    for k in range(26):
                        while count[k] > 0:
                            result.append(chr(ord('a') + k))
                            count[k] -= 1
                    return ''.join(result)
            return ''
        return ''.join(result)