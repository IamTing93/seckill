def write(num):
    with open(r'G:\java_workspace\seckill-ting\seckill\performance_test\data\user.csv', 'w+') as f:
        for i in range(1, int(num) + 1):
            f.write('%d\n' % i)


if __name__ == '__main__':
    value = input('input a int: ')
    write(value)
    print('done')