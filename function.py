def dollarizer(string: str) -> str:
    return string.replace('s', '$')

def eurizer(string: str) -> str:
    return string.replace('e', '€')

def replacer():
    word = input("Enter a word: ")
    c1 = input("Enter a character1: ")
    c2 = input("Enter a character2: ")
    print(word.replace(c1, c2))

def wonky_text(text: str) -> str:
    result = dollarizer(text)
    result = eurizer(result)
    result = result.replace("l", "£")
    return result

def celsius_to_fahrenheit(celsius: float) -> float:
    fahrenheit = (celsius * 1.8) + 32
    return fahrenheit


def age_in_day(age: int) -> int:
    day = age * 365
    return day

def simple_interest(amount: float, rate: float, years: int) -> float:
    interest = amount * rate * years
    return interest

def plan_finances(amount: float, rate: float, years: int, final_amount: float) -> bool:
    if simple_interest(amount, rate, years) < final_amount:
        return False
    else:
        return True

def main():
    print(dollarizer(input("Enter a word: ")))
    print(eurizer(input("Enter a word: ")))
    replacer()
    print(wonky_text(input("Enter a word: ")))
    print(celsius_to_fahrenheit(float(input("Enter a celsius: "))))
    print(age_in_day(int(input("Enter a years: "))))
    print(simple_interest(float(input("Enter a amount: ")), float(input("Enter a rate: ")), int(input("Enter a years: "))))
    print(plan_finances(float(input("Enter a amount: ")), float(input("Enter a rate: ")), int(input("Enter a years: ")), float(input("Enter a desired final amount: "))))


if __name__ == '__main__':
    main()


