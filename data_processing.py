from mimetypes import inited


def to_uppercase():
    sentence = input("Enter a sentence: ")
    print(sentence.upper())

def count_words():
    paragraph = input("Enter a paragraph: ")
    words = paragraph.split()
    print(len(words))

def is_all_digits():
    s = input("Enter a String: ")
    print(s.isdigit())

def replace_a_with_o():
    s1 = input("Enter a String: ")
    result = s1.replace('a', 'o').replace('A', 'O')
    print(result)

def print_initials():
    full_name = input("Enter your full name: ")
    parts = full_name.split()
    initials = '.'.join(p[0].upper() for p in parts)
    print(initials)

def print_length():
    s = input("Enter a String: ")
    print(len(s))

def main():
    print("1) Uppercase converter")
    to_uppercase()
    print("\n2) Word counter")
    count_words()
    print("\n3) Digits check")
    is_all_digits()
    print("\n4) Replace 'a' with 'o'")
    replace_a_with_o()
    print("\n5) Print initials")
    print_initials()
    print("\n6) String length")
    print_length()

if __name__ == "__main__":
    main()